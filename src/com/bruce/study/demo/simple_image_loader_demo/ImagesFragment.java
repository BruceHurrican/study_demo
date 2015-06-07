//package com.bruce.study.demo.simple_image_loader_demo;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import com.bruce.study.demo.R;
//
///**
// * Created by BruceHurrican on 2015/6/7.
// */
//public class ImagesFragment extends Fragment {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_gridview_images, container, false);
//        GridView gridView = (GridView) view.findViewById(R.id.id_gridview);
//        gridView.setAdapter(new ImageItemAdapter(getActivity(),0,SimpleImageLoaderActivity.imageThumbUrls));
//        return view;
//    }
//
//    private class ImageItemAdapter extends ArrayAdapter<String> {
//
//        public ImageItemAdapter(Context context, int resource, String[] datas) {
//            super(getActivity(), 0, datas);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (null == convertView) {
//                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_image_in_images_fragment, parent, false);
//            }
//            ImageView imageView = (ImageView) convertView.findViewById(R.id.id_img);
//            // todo 加载图片
////            SimpleImageLoader.getInstance().displayImage(imageView,getItem(position));
//            return convertView;
//        }
//    }
//}
