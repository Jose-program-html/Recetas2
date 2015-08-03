package shary.recetas.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import shary.recetas.R;

/**
 * Created by Shary on 27/06/2015.
 */
public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        VideoView videoView = (VideoView) rootView.findViewById(R.id.videoView);
        try {
            MediaController mediaController = new MediaController(this.getActivity());
            mediaController.setAnchorView(videoView);
            Uri uri = Uri.parse(getString(R.string.api_endpoint)+"/assets/video/video.mp4");
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(uri);
            videoView.start();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
