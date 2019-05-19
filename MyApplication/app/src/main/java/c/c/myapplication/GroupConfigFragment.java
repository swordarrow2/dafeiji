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

public class GroupConfigFragment extends Fragment {

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
			  public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
				  final GroupConfig groupConfig = (GroupConfig) adapterView.getItemAtPosition(position);
				  final View v = getActivity().getLayoutInflater().inflate(R.layout.group_config, null);
				  final EditText et = (EditText) v.findViewById(R.id.group_configTextView_groupNumber);
				  et.setText(String.valueOf(groupConfig.groupNumber));
				  final Switch repeat = (Switch) v.findViewById(R.id.group_configSwitch_repeat);
				  final Switch setu = (Switch) v.findViewById(R.id.group_configSwitch_setu);
				  final Switch pohai = (Switch) v.findViewById(R.id.group_configSwitch_pohai);
				  final Switch dic = (Switch) v.findViewById(R.id.group_configSwitch_dic);
				  final Switch bilibili = (Switch) v.findViewById(R.id.group_configSwitch_bilibili);
				  final Switch cuigeng = (Switch) v.findViewById(R.id.group_configSwitch_cuigeng);
				  final Switch searchPicture = (Switch) v.findViewById(R.id.group_configSwitch_soutu);
				  final Switch checkLink = (Switch) v.findViewById(R.id.group_configSwitch_link);
				  final Switch roll = (Switch) v.findViewById(R.id.group_configSwitch_roll);
				  final Switch barcode = (Switch) v.findViewById(R.id.group_configSwitch_barcode);
				  final Switch kuiping = (Switch) v.findViewById(R.id.group_configSwitch_kuiping);
				  final Switch cqma = (Switch) v.findViewById(R.id.group_configSwitch_cqcode);
				  final Switch zan = (Switch) v.findViewById(R.id.group_configSwitch_zan);
				  final Switch moshenfusong = (Switch) v.findViewById(R.id.group_configSwitch_mishenfusong);
				  repeat.setChecked(groupConfig.isRepeat());
				  setu.setChecked(groupConfig.isSetu());
				  pohai.setChecked(groupConfig.isPohai());
				  dic.setChecked(groupConfig.isDic());
				  bilibili.setChecked(groupConfig.isBilibiliCheck());
				  cuigeng.setChecked(groupConfig.isCuigeng());
				  searchPicture.setChecked(groupConfig.isSearchPic());
				  checkLink.setChecked(groupConfig.isCheckLink());
				  roll.setChecked(groupConfig.isRoll());
				  barcode.setChecked(groupConfig.isBarcode());
				  kuiping.setChecked(groupConfig.isKuiping());
				  cqma.setChecked(groupConfig.isCqCode());
				  zan.setChecked(groupConfig.isZan());
				  moshenfusong.setChecked(groupConfig.isMoshenfusong());

				  new AlertDialog.Builder(getActivity())
					.setView(v)
					.setTitle("编辑")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface p1, int p2) {
							groupConfig.groupNumber = Long.parseLong(et.getText().toString());
							groupConfig.setRepeat(repeat.isChecked());
							groupConfig.setSetu(setu.isChecked());
							groupConfig.setPohai(pohai.isChecked());
							groupConfig.setDic(dic.isChecked());
							groupConfig.setBilibiliCheck(bilibili.isChecked());
							groupConfig.setCuigeng(cuigeng.isChecked());
							groupConfig.setSearchPic(searchPicture.isChecked());
							groupConfig.setCheckLink(checkLink.isChecked());
							groupConfig.setRoll(roll.isChecked());
							groupConfig.setBarcode(barcode.isChecked());
							groupConfig.setKuiping(kuiping.isChecked());
							groupConfig.setCqCode(cqma.isChecked());
							groupConfig.setZan(zan.isChecked());
							groupConfig.setMoshenfusong(moshenfusong.isChecked());
							MainActivity.instence.configJavaBean.groupConfigs.set(MainActivity.instence.findPosition(groupConfig), groupConfig);
							MainActivity.instence.networkManager.send(NetworkType.setGroup,MainActivity.instence.findPosition(groupConfig) + " " +MainActivity.instence.gson.toJson(groupConfig));
						  }
					  }).setNegativeButton("取消",null).show();
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
							GroupConfig groupConfig = (GroupConfig) adapterView.getItemAtPosition(position);
							MainActivity.instence.configJavaBean.groupConfigs.remove(MainActivity.instence.findPosition(groupConfig));
							adapterView.removeViewAt(position);
							((BaseAdapter) adapterView.getAdapter()).notifyDataSetChanged();
							MainActivity.instence.networkManager.send(NetworkType.removeGroup, String.valueOf(MainActivity.instence.findPosition(groupConfig)));
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
