package com.example.myapplication.ui.fragment.reading;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import com.example.myapplication.R;
import com.example.myapplication.ui.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReadingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReadingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadingFragment extends Fragment implements ReadingView {
    ReadingPresenter readingPresenter;
    Button button;
    EditText edtId;
    EditText edtName;
    EditText edtContent;

    private OnFragmentInteractionListener mListener;

    public ReadingFragment() {
        // Required empty public constructor
    }

    public static ReadingFragment newInstance() {

        ReadingFragment fragment = new ReadingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readingPresenter = new ReadingPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reading, container, false);
        button = (Button) view.findViewById(R.id.btnTestApi);
        edtContent = (EditText) view.findViewById(R.id.edt_content);
        edtId = (EditText) view.findViewById(R.id.edt_id_story);
        edtName = (EditText) view.findViewById(R.id.edt_name_story);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readingPresenter.callApiDemo(edtId.getText().toString(), edtName.getText().toString(), edtContent.getText().toString());
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onCallApiSuccess() {

    }

    @Override
    public void onRequestFail(String s) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
