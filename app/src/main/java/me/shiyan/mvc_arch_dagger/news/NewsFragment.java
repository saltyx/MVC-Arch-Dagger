package me.shiyan.mvc_arch_dagger.news;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import me.shiyan.mvc_arch_dagger.R;
import me.shiyan.mvc_arch_dagger.bean.BaseNews;
import me.shiyan.mvc_arch_dagger.utils.Category;
import me.shiyan.mvc_arch_dagger.utils.Device;
import me.shiyan.mvc_arch_dagger.utils.HttpUtils;
import me.shiyan.mvc_arch_dagger.utils.Log;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsContract.View {

    public static String ARGUMENT_CATEGORY = "CATEGORY";
    public static String ARGUMENT_DEVICE = "DEVICE";

    private NewsContract.Presenter mPresenter;

    private ListView mDataListView;

    private Log mLog = new Log(getClass().getName());

    public NewsFragment() {

    }

    public static NewsFragment newInstance(Category category,Device device){

        Bundle arg = new Bundle();
        arg.putSerializable(ARGUMENT_CATEGORY,category);
        arg.putSerializable(ARGUMENT_DEVICE,device);
        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(arg);
        return newsFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter:
                showPopupWindow();
                break;
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.news_classifies_item,menu);
    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showPopupWindow() {
        PopupMenu popup = new PopupMenu(getContext(), getActivity().findViewById(R.id.filter));
        popup.getMenuInflater().inflate(R.menu.menu_category,popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tourNews:
                        mPresenter.loadFromRemote();
                        break;
                    case R.id.topRatedNews:
                        mPresenter.loadFromRemote();
                        break;
                    case R.id.appNews:
                        mPresenter.loadFromRemote();
                        break;
                    case R.id.gameNews:
                        mPresenter.loadFromRemote();
                        break;
                }
                return true;
            }
        });

        popup.show();
    }

    @Override
    public void showByCategory(Category category) {

    }


    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        if (presenter != null){
            this.mPresenter = presenter;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.news_frag, container, false);

        mDataListView = (ListView) root.findViewById(R.id.listViewNews);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(),R.color.colorNewsActBottom));
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        swipeRefreshLayout.setProgressViewEndTarget(true, 100);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //// TODO: 2016/8/22 从网络刷新数据存到本地 
            }
        });
        setHasOptionsMenu(true);
        return root;

    }

    private static class NewsAdapter extends BaseAdapter{

        ArrayList<BaseNews> data;

        public NewsAdapter(ArrayList<BaseNews> data) {
            this.data = data;
        }

        public void replaceData(ArrayList<BaseNews> data){
            this.data = data;
            notifyDataSetChanged();
        }
        
        public void addData(BaseNews news){
            data.add(news);
            notifyDataSetChanged();
        }
        
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public BaseNews getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null){
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.news_item, parent, false);
            }

            final BaseNews baseNews = getItem(position);

            final ImageView imageView = (ImageView) rowView.findViewById(R.id.newsItemImage);
            TextView title = (TextView) rowView.findViewById(R.id.newsItemTitle);
            TextView descr = (TextView) rowView.findViewById(R.id.newsItemDescr);
            TextView link = (TextView) rowView.findViewById(R.id.newsItemLink);
            TextView ref = (TextView) rowView.findViewById(R.id.newsItemRef);
            TextView time = (TextView) rowView.findViewById(R.id.newsItemTime);

            HttpUtils httpUtils = new HttpUtils();
            httpUtils.getImage(baseNews.thumb, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //// TODO: 2016/8/22 设置无图片 
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        InputStream inputStream = response.body().byteStream();
                        final Bitmap img = BitmapFactory.decodeStream(inputStream);
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(img);
                            }
                        });
                    }else {
                        
                    }

                }
            });
            
            title.setText(baseNews.title);
            descr.setText(baseNews.descr);
            link.setText(baseNews.link);
            ref.setText(baseNews.refinfo);
            time.setText(baseNews.time);
            return rowView;
        }
    }
}
