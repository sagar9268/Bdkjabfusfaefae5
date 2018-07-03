package co.bingleapp.bingle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Settings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Settings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Settings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSettingsFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
       super.onAttach(context);
       if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
       } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
       }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSettingsFragmentInteraction(Uri uri);

    }
}

//Add the Form Elements programmatically in your activity
//// initialize variables
//mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//mFormBuilder = new FormBuildHelper(this, mRecyclerView);
//
//// declare form elements
//FormHeader header = FormHeader.createInstance("Personal Info");
//FormElementTextEmail element = FormElementTextEmail.createInstance().setTitle("Email").setHint("Enter Email");
//
//// add them in a list
//List<FormObject> formItems = new ArrayList<>();
//formItems.add(header);
//formItems.add(element);
//
//// build and display the form
//mFormBuilder.addFormElements(formItems);
//mFormBuilder.refreshView();
// email input
/*FormElementTextEmail element = FormElementTextEmail.createInstance().setTitle("Email").setHint("Enter Email");

    // phone number input
    FormElementTextPhone element = FormElementTextPhone.createInstance().setTitle("Phone").setValue("+8801712345678");

    // single line text input
    FormElementTextSingleLine element = FormElementTextSingleLine.createInstance().setTitle("Location").setValue("Dhaka");

    // multi line text input (default 4)
    FormElementTextMultiLine element = FormElementTextMultiLine.createInstance().setTitle("Address");

    // number element input
    FormElementTextNumber element = FormElementTextNumber.createInstance().setTitle("Zip Code").setValue("1000");

    // date picker input
    FormElementPickerDate element = FormElementPickerDate.createInstance().setTitle("Date").setDateFormat("MMM dd, yyyy");

    // time picker input
    FormElementPickerTime element = FormElementPickerTime.createInstance().setTitle("Time").setTimeFormat("KK hh");

    // password input
    FormElementTextPassword element = FormElementTextPassword.createInstance().setTitle("Password").setValue("abcd1234");

    // switch input
    FormElementSwitch element = FormElementSwitch.createInstance().setTitle("Frozen?").setSwitchTexts("Yes", "No");

    // single item picker input
    List<String> fruits = new ArrayList<>();
fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");
        FormElementPickerSingle element = FormElementPickerSingle.createInstance().setTitle("Single Item").setOptions(fruits).setPickerTitle("Pick any item");

// multiple items picker input
        List<String> fruits = new ArrayList<>();
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");
        fruits.add("Guava");
        FormElementPickerMulti element = FormElementPickerMulti.createInstance().setTitle("Multi Items").setOptions(fruits).setPickerTitle("Pick one or more").setNegativeText("reset");
        */