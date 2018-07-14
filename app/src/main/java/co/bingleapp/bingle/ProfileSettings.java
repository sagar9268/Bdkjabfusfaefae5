package co.bingleapp.bingle;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileSettings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSettings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static final int DEFAULT_BIO_LENGTH_LIMIT = 1000;
    Button preferenceSaveButton;
    Button preferenceEditButton;
    private EditText mCollegeEditText;
    private EditText mInterestsEditText;
    private EditText mAgeEditText;
    private TextView mCollegeEditTextView;
    private TextView mInterestsEditTextView;
    private TextView mAgeEditTextView;

    //firebase instances
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mCollegeDatabaseReference;
    private DatabaseReference mInterestsDatabaseReference;
    private DatabaseReference mAgeDatabaseReference;

    private OnFragmentInteractionListener mListener;

    public ProfileSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileSettings newInstance(String param1, String param2) {
        ProfileSettings fragment = new ProfileSettings();
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
        View rootView = inflater.inflate(R.layout.fragment_profile_settings, container, false);
        //Initialize firebase component
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mCollegeDatabaseReference = mFirebaseDatabase.getReference().child("College");
        mInterestsDatabaseReference = mFirebaseDatabase.getReference().child("Interests");
        mAgeDatabaseReference = mFirebaseDatabase.getReference().child("Age");

        //Initialize reference to views
     /*   mCollegeEditText = (EditText) rootView.findViewById(R.id.editCollege);
        mInterestsEditText = (EditText) rootView.findViewById(R.id.editInterests);
        mAgeEditText = (EditText) rootView.findViewById(R.id.editAge);
        mCollegeEditTextView = (TextView) rootView.findViewById(R.id.textViewEditCollege);
        mInterestsEditTextView = (TextView) rootView.findViewById(R.id.textViewEditInterests);
        mAgeEditTextView = (TextView) rootView.findViewById(R.id.textViewEditAge);
        preferenceSaveButton = (Button) rootView.findViewById(R.id.buttonBioPreferenceSave);
        preferenceEditButton = (Button) rootView.findViewById(R.id.buttonBioPreferenceEdit);

        //Enable save button when there's text change
        mCollegeEditText.addTextChangedListener(watcher);
        mInterestsEditText.addTextChangedListener(watcher);
        mAgeEditText.addTextChangedListener(watcher);
*/
     //   mBioEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_BIO_LENGTH_LIMIT)});

        //Layout related code
        preferenceSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pushing values to database
                mCollegeDatabaseReference.push().setValue(mCollegeEditText.getText().toString());
                mInterestsDatabaseReference.push().setValue(mInterestsEditText.getText().toString());
                mAgeDatabaseReference.push().setValue(mAgeEditText.getText().toString());
                //setting text to text views
                mCollegeEditTextView.setVisibility(View.VISIBLE);
                mInterestsEditTextView.setVisibility(View.VISIBLE);
                mAgeEditTextView.setVisibility(View.VISIBLE);
                mCollegeEditTextView.setText(mCollegeEditText.getText().toString());
                mInterestsEditTextView.setText(mInterestsEditText.getText().toString());
                mAgeEditTextView.setText(mAgeEditText.getText().toString());
                //change visibility
                mCollegeEditText.setVisibility(View.INVISIBLE);
                mInterestsEditText.setVisibility(View.INVISIBLE);
                mAgeEditText.setVisibility(View.INVISIBLE);

                mListener.onProfileSettingsFragmentInteraction();
            }
        });

        preferenceEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCollegeEditTextView.setVisibility(View.INVISIBLE);
                mInterestsEditTextView.setVisibility(View.INVISIBLE);
                mAgeEditTextView.setVisibility(View.INVISIBLE);
                //Change visibility
                mCollegeEditText.setVisibility(View.VISIBLE);
                mInterestsEditText.setVisibility(View.VISIBLE);
                mAgeEditText.setVisibility(View.VISIBLE);
                //Set text to edit
                mCollegeEditText.setText(mCollegeEditTextView.getText().toString());
                mInterestsEditText.setText(mInterestsEditTextView.getText().toString());
                mAgeEditText.setText(mAgeEditTextView.getText().toString());
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onProfileSettingsFragmentInteraction();
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

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.toString().trim().length()>0){
                preferenceSaveButton.setEnabled(true);
            } else {
                preferenceSaveButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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
        void onProfileSettingsFragmentInteraction();

    }
}
