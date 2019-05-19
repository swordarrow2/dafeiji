package c.c.myapplication;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.github.clans.fab.*;
import com.meng.grzxv2.*;
import com.meng.grzxv2.javaBean.*;
import java.util.*;

import android.support.v4.app.Fragment;

public class QQNotReplyFragment extends Fragment {

    public ListView mListView;
    private FloatingActionButton mFab;
    private int mPreviousVisibleItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
	  }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.list);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
	  }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Locale[] availableLocales = Locale.getAvailableLocales();
        List<String> locales = new ArrayList<>();
        for (Locale locale : availableLocales) {
            locales.add(locale.getDisplayName());
		  }

		//   mListView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, locales));

		mListView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(final AdapterView<?> adapterView, View view, final int position, long l) {
				  final EditText editText = new EditText(getActivity());
				  final long number = adapterView.getItemAtPosition(position);
				  editText.setText(String.valueOf(number));
				  new AlertDialog.Builder(getActivity())
					.setView(editText)
					.setTitle("编辑")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface p11, int p2) {
							new AlertDialog.Builder(getActivity())
							  .setTitle("确定修改吗")
							  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
								  @Override
								  public void onClick(DialogInterface p11, int p2) {
									  MainActivity.instence.configJavaBean.QQNotReply.set(MainActivity.instence.findPosition(number), Long.parseLong(editText.getText().toString()));
									  ((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
									  MainActivity.instence.networkManager.send(NetworkType.setNotReplyUser, MainActivity.instence.findPosition(number) + " " + editText.getText().toString());
									}
								}).setNegativeButton("取消", null).show();
						  }
					  }).setNegativeButton("取消", null).show();
				}
			});

		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			  @Override
			  public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long id) {

				  new AlertDialog.Builder(getActivity())
					.setTitle("确定删除吗")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int which) {
							long qq = adapterView.getItemIdAtPosition(position);
							MainActivity.instence.configJavaBean.QQNotReply.remove(MainActivity.instence.findPosition(qq));
							adapterView.removeViewAt(position);
							((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
							MainActivity.instence.networkManager.send(NetworkType.removeNotReplyUser, String.valueOf(MainActivity.instence.findPosition(qq)));
						  }
					  }).setNegativeButton("取消", null).show();
				  return true;
				}
			});


        mFab.hide(false);
        new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
				  mFab.show(true);
				  mFab.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
				  mFab.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
				}
			}, 300);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
			  @Override
			  public void onScrollStateChanged(AbsListView view, int scrollState) {
				}

			  @Override
			  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				  if (firstVisibleItem > mPreviousVisibleItem) {
					  mFab.hide(true);
					} else if (firstVisibleItem < mPreviousVisibleItem) {
					  mFab.show(true);
					}
				  mPreviousVisibleItem = firstVisibleItem;
				}
			});
	  }
  }
