package com.serveroverload.emittor_key;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
	 * A placeholder fragment containing a simple view.
	 */
	public class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		ImageView img;

		int[] bgs = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher };

		public PlaceholderFragment() {
		}

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_pager,
					container, false);
			TextView textView = (TextView) rootView
					.findViewById(R.id.section_label);
			textView.setText(getString(R.string.app_name, getArguments()
					.getInt(ARG_SECTION_NUMBER)));

			img = (ImageView) rootView.findViewById(R.id.section_img);
			img.setBackgroundResource(bgs[getArguments().getInt(
					ARG_SECTION_NUMBER) - 1]);

			return rootView;
		}

	}
