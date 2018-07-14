package co.bingleapp.bingle;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;


public class Profile_Fillup extends AppCompatActivity implements VerticalStepperForm {


    private VerticalStepperFormLayout verticalStepperForm;

    //Elements in layout
    public RadioGroup mRadioGroup;
    public DatePicker mdatePicker;
    public EditText phone;
    public ProgressDialog progressDialog;
    public RadioButton mCollege;



    // Variables
    public String userGender;
    public EditText name;
    int dobday;
    int dobmonth;
    int dobyear;
    String college;
    Calendar mCalendar;

    public void stepComplete(){
        verticalStepperForm.setActiveStepAsCompleted();
        verticalStepperForm.goToNextStep();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_fillup);

        String[] mySteps = {"Name", "Gender", "Date of Birth", "Education", "Hobbies"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();

    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createNameStep();
                break;
            case 1:
                view = createGenderStep();
                break;
            case 2:
                view = createDOBStep();
                break;
            case 3:
                view = createEducationStep();
                break;
            case 4:
                view = createHobbiesStep();
                break;
        }
        return view;
    }

    private View createNameStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        name = new EditText(this);
        name.setSingleLine(true);
        name.setHint("Your name");

        return name;
    }

    private View createGenderStep() {
        // In this case we generate the view by inflating a XML file
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout genderLayoutContent = (LinearLayout) inflater.inflate(R.layout.activity_gender, null, false);

        mRadioGroup = findViewById(R.id.radiogroup);

        return genderLayoutContent;

    }

    private View createDOBStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout dobLayoutContent = (LinearLayout) inflater.inflate(R.layout.dobpicker, null, false);

        mdatePicker = (findViewById(R.id.dobdatePicker));

        return dobLayoutContent;
    }

    private View createEducationStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout educationLayoutContent = (LinearLayout) inflater.inflate(R.layout.selectcollege, null, false);

        mCollege = findViewById(R.id.collegeSelect_radioButton);

        return educationLayoutContent;
    }

    private View createHobbiesStep() {
        phone = new EditText(this);
        phone.setSingleLine(true);
        phone.setHint("Your name");

        return phone;
    }

    @Override
    public void onStepOpening(int stepNumber) {
        mRadioGroup = findViewById(R.id.radiogroup);
        mdatePicker = (findViewById(R.id.dobdatePicker));
        mCollege = findViewById(R.id.collegeSelect_radioButton);


    }

    @Override
    public void onContinue(int stepNumber) {
        switch (stepNumber) {
            case 0:
                checkName();
                break;
            case 1:
                checkGender();
                break;
            case 2:
                checkDOB();
                break;
            case 3:
                checkEducation();
                break;

        }
        }



    private void checkName() {
        if(name.length() >= 3 && name.length() <= 40) {
            stepComplete();
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            String errorMessage = "The name must have between 3 and 40 characters";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }
    }

    private void checkGender() {
        if (mRadioGroup.getCheckedRadioButtonId() == -1) {
            String errorMessage = "Please select your gender";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        } else {
            int selectId = mRadioGroup.getCheckedRadioButtonId();
            final RadioButton selectedGender = findViewById(selectId);
            userGender = selectedGender.getText().toString();
            stepComplete();
        }
    }

    private void checkDOB() {
        dobday = mdatePicker.getDayOfMonth();
        dobmonth = 1+mdatePicker.getMonth();
        dobyear = mdatePicker.getYear();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        mCalendar = Calendar.getInstance();
        int day;
        int month;
        int year;
        day = mCalendar.get(Calendar.DAY_OF_MONTH);
        month = mCalendar.get(Calendar.MONTH);
        year = mCalendar.get(Calendar.YEAR);
        if(year - dobyear > 18)
        {
            stepComplete();
        }
        else if(year - dobyear == 18 && month > dobmonth)
        {
            stepComplete();
        }
        else if(year - dobyear == 18 && month == dobmonth && day >= dobday)
        {
            stepComplete();
        }
        else
        {
            String errorMessage = "You must be 18 years of age to Sign In !";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }
    }

    private void checkEducation(){
        if (mCollege.isChecked()) {
            college = mCollege.getText().toString();
            stepComplete();
        }
        else {
            String errorMessage = "Please select your college";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }
    }




    @Override
    public void sendData() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.vertical_form_stepper_form_sending_data_message));
    }
}