package com.meng.grzxConfig.MaterialDesign.fragment;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;
import android.widget.AdapterView.*;

import com.github.clans.fab.*;
import com.meng.grzxConfig.MaterialDesign.R;
import com.meng.grzxConfig.MaterialDesign.activity.DicEdit;
import com.meng.grzxConfig.MaterialDesign.helpers.NetworkType;
import com.meng.grzxConfig.MaterialDesign.javaBean.*;

import android.support.v4.app.Fragment;

import com.meng.grzxConfig.MaterialDesign.activity.MainActivity;

public class GroupConfigFragment extends Fragment {

    public ListView mListView;
    private FloatingActionButton mFab;

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
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final GroupConfig groupConfig = (GroupConfig) adapterView.getItemAtPosition(position);
                final View v = getActivity().getLayoutInflater().inflate(R.layout.group_config, null);
                final EditText et = (EditText) v.findViewById(R.id.group_configTextView_groupNumber);
                et.setText(String.valueOf(groupConfig.groupNumber));
                final Switch repeat = (Switch) v.findViewById(R.id.group_configSwitch_repeat);
                final RadioGroup banMode = (RadioGroup) v.findViewById(R.id.group_configSwitch_banMode);
                final Switch setu = (Switch) v.findViewById(R.id.group_configSwitch_setu);
                final Switch pohai = (Switch) v.findViewById(R.id.group_configSwitch_pohai);
                final Switch dic = (Switch) v.findViewById(R.id.group_configSwitch_dic);
                final Button editDic = (Button) v.findViewById(R.id.group_config_edit_dic);
                editDic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), DicEdit.class);
                        intent.putExtra("num", groupConfig.groupNumber);
                        startActivity(intent);
                    }
                });
                final Switch bilibili = (Switch) v.findViewById(R.id.group_configSwitch_bilibili);
                final Switch cuigeng = (Switch) v.findViewById(R.id.group_configSwitch_cuigeng);
                final Switch searchPicture = (Switch) v.findViewById(R.id.group_configSwitch_soutu);
                final Switch checkLink = (Switch) v.findViewById(R.id.group_configSwitch_link);
                final Switch roll = (Switch) v.findViewById(R.id.group_configSwitch_roll);
                final Switch barcode = (Switch) v.findViewById(R.id.group_configSwitch_barcode);
                final Switch kuiping = (Switch) v.findViewById(R.id.group_configSwitch_kuiping);
                final Switch cqma = (Switch) v.findViewById(R.id.group_configSwitch_cqcode);
                final Switch nvzhuang = (Switch) v.findViewById(R.id.group_configSwitch_zan);
                final Switch moshenfusong = (Switch) v.findViewById(R.id.group_configSwitch_mishenfusong);
                final Switch chehui = (Switch) v.findViewById(R.id.group_configSwitch_chehui);

                repeat.setChecked(groupConfig.isRepeat());
                ((RadioButton) banMode.getChildAt(groupConfig.repeatMode)).setChecked(true);
                setu.setChecked(groupConfig.isSetu());
                pohai.setChecked(groupConfig.isPohai());
                dic.setChecked(groupConfig.isDic());
                bilibili.setChecked(groupConfig.isBilibiliCheck());
                cuigeng.setChecked(groupConfig.isCuigeng());
                searchPicture.setChecked(groupConfig.isSearchPic());
                checkLink.setChecked(groupConfig.isSleep());
                roll.setChecked(groupConfig.isRoll());
                barcode.setChecked(groupConfig.isBarcode());
                kuiping.setChecked(groupConfig.isKuiping());
                cqma.setChecked(groupConfig.isCqCode());
                nvzhuang.setChecked(groupConfig.isNvZhuang());
                moshenfusong.setChecked(groupConfig.isMoshenfusong());
                chehui.setChecked(groupConfig.isCheHuiMoTu());
                new AlertDialog.Builder(getActivity())
                        .setView(v)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface p1, int p2) {
                                groupConfig.groupNumber = Long.parseLong(et.getText().toString());
                                groupConfig.setRepeat(repeat.isChecked());
                                switch (banMode.getCheckedRadioButtonId()) {
                                    case R.id.group_config_radio_none:
                                        groupConfig.repeatMode = 0;
                                        break;
                                    case R.id.group_config_radio_eluosi:
                                        groupConfig.repeatMode = 1;
                                        break;
                                    case R.id.group_config_radio_all:
                                        groupConfig.repeatMode = 2;
                                        break;
                                }
                                groupConfig.setSetu(setu.isChecked());
                                groupConfig.setPohai(pohai.isChecked());
                                groupConfig.setDic(dic.isChecked());
                                groupConfig.setBilibiliCheck(bilibili.isChecked());
                                groupConfig.setCuigeng(cuigeng.isChecked());
                                groupConfig.setSearchPic(searchPicture.isChecked());
                                groupConfig.setSleep(checkLink.isChecked());
                                groupConfig.setRoll(roll.isChecked());
                                groupConfig.setBarcode(barcode.isChecked());
                                groupConfig.setKuiping(kuiping.isChecked());
                                groupConfig.setCqCode(cqma.isChecked());
                                groupConfig.setZan(nvzhuang.isChecked());
                                groupConfig.setMoshenfusong(moshenfusong.isChecked());
                                groupConfig.setCheHuiMoTu(chehui.isChecked());
                                MainActivity.instence.networkManager.send(NetworkType.setGroup, MainActivity.instence.gson.toJson(groupConfig), MainActivity.instence.groupConfigAdapter);
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
                                GroupConfig groupConfig = (GroupConfig) adapterView.getItemAtPosition(position);
                                MainActivity.instence.configJavaBean.groupConfigs.remove(groupConfig);
                                MainActivity.instence.networkManager.send(NetworkType.removeGroup, MainActivity.instence.gson.toJson(groupConfig), MainActivity.instence.groupConfigAdapter);
                            }
                        }).setNegativeButton("取消", null).show();
                return true;
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = new EditText(getActivity());
                new AlertDialog.Builder(getActivity())
                        .setView(editText)
                        .setTitle("编辑")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface p11, int p2) {

                                new AlertDialog.Builder(getActivity())
                                        .setTitle("确定添加吗")
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface p11, int p2) {
                                                long inputLong = Long.parseLong(editText.getText().toString());
                                                for (GroupConfig groupConfig : MainActivity.instence.configJavaBean.groupConfigs) {
                                                    if (groupConfig.groupNumber == inputLong) {
                                                        return;
                                                    }
                                                }
                                                GroupConfig groupConfig = new GroupConfig();
                                                groupConfig.groupNumber = inputLong;
                                                MainActivity.instence.configJavaBean.groupConfigs.add(groupConfig);
                                                MainActivity.instence.networkManager.send(NetworkType.addGroup, MainActivity.instence.gson.toJson(groupConfig), MainActivity.instence.groupConfigAdapter);
                                            }
                                        }).setNegativeButton("取消", null).show();
                            }
                        }).setNegativeButton("取消", null).show();
            }
        });
        mFab.hide(false);
        mFab.show(true);
        mFab.setShowAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.show_from_bottom));
        mFab.setHideAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.hide_to_bottom));
    }
}
