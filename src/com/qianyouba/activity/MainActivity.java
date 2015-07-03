package com.qianyouba.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.*;
import android.widget.*;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.qianyouba.R;
import com.qianyouba.bean.GroupItemData;
import com.qianyouba.bean.ViewData;
import com.qianyouba.fragment.BaseFragment;
import com.qianyouba.fragment.GroupFragment;
import com.qianyouba.fragment.HomeFragment;
import com.qianyouba.fragment.MeFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        BaseFragment.OnFragListener, MeFragment.OnWidgetClickListener {
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    /**
     * custom_title_search.xml 控件元素 --begin--
     */
    private LinearLayout layout_search;
    private RelativeLayout layout_title;
    private Button btn_back, close_btn, search_btn, switch_btn, btn_add_group;
    private TextView text_title;
    private EditText search_content;
    /**
     * --end--
     */
    private TextView tv_tongge, tv_tongjie, tv_all;
    private long exitTime = 0L;
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, GroupFragment.class,
            MeFragment.class};

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn,
            R.drawable.tab_group_btn, R.drawable.tab_me_btn};

    // Tab选项卡的文字
    private String mTextviewArray[];
    // Tab选项卡的tag
    private String mTabTag[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mTextviewArray = getResources().getStringArray(R.array.tab_menu);
        mTabTag = getResources().getStringArray(R.array.tab_tag);
        initView();
    }

    private void initView() {
        layout_title = (RelativeLayout) findViewById(R.id.rl_title);
        layout_search = (LinearLayout) findViewById(R.id.ll_search);
        btn_back = (Button) findViewById(R.id.btn_back);
        close_btn = (Button) findViewById(R.id.btn_close);
        search_btn = (Button) findViewById(R.id.btn_search);
        switch_btn = (Button) findViewById(R.id.btn_switch);
        text_title = (TextView) findViewById(R.id.tv_title);
        search_content = (EditText) findViewById(R.id.et_search_content);
        btn_add_group = (Button) findViewById(R.id.btn_add_group);

        search_btn.setOnClickListener(this);
        switch_btn.setOnClickListener(this);
        close_btn.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_add_group.setOnClickListener(this);
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(mTabTag[i]).setIndicator(
                    getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.home_btn_bg);
        }
        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabTag) {
                if (tabTag.equalsIgnoreCase("home")) {
                    layout_title.setVisibility(View.GONE);
                    layout_search.setVisibility(View.VISIBLE);
                } else if (tabTag.equalsIgnoreCase("group")) {
                    layout_title.setVisibility(View.VISIBLE);
                    layout_search.setVisibility(View.GONE);
                    btn_back.setVisibility(View.INVISIBLE);
                    btn_add_group.setVisibility(View.VISIBLE);
                    text_title.setText(getResources().getString(
                            R.string.group_title));
                } else if (tabTag.equalsIgnoreCase("me")) {
                    layout_title.setVisibility(View.VISIBLE);
                    layout_search.setVisibility(View.GONE);
                    btn_back.setVisibility(View.INVISIBLE);
                    btn_add_group.setVisibility(View.INVISIBLE);
                    text_title.setText(getResources().getString(
                            R.string.me_title));
                }
            }
        });
    }

    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);
        /*
		 * ImageView imageView = (ImageView) view.findViewById(R.id.iamge_view);
		 * imageView.setImageResource(mImageViewArray[index]);
		 */
        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(mTextviewArray[index]);
        Drawable drawable = getResources().getDrawable(mImageViewArray[index]);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        return view;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(MainActivity.this,
                        getResources().getString(R.string.press_again_exit),
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return false;
    }

    /**
     * 使用popupwindow实现弹出菜单
     */
    private PopupWindow popupWindow;

    private void initPopWindow() {
        View popView = LayoutInflater.from(this).inflate(
                R.layout.qian_popup_menu, null);

        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopupAnimation);

        tv_tongge = (TextView) popView.findViewById(R.id.tv_tongge);
        tv_tongjie = (TextView) popView.findViewById(R.id.tv_tongjie);
        tv_all = (TextView) popView.findViewById(R.id.tv_all);

        tv_tongge.setOnClickListener(this);
        tv_tongjie.setOnClickListener(this);
        tv_all.setOnClickListener(this);
    }

    public void showPop(View parent, int x, int y) {
        // 设置popwindow显示位置
        popupWindow.showAtLocation(parent, 0, x, y);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        // 设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        if (popupWindow.isShowing()) {

        }

    }

    /**
     * 使用popupmenu实现选择通哥通姐的菜单
     */
    private PopupMenu popupMenu;

	/*
	 * private void initPopupMenu() { popupMenu = new PopupMenu(this,
	 * switch_btn); // Inflating the Popup using xml file
	 * popupMenu.getMenuInflater().inflate(R.menu.popup_menu,
	 * popupMenu.getMenu()); // registering popup with OnMenuItemClickListener
	 * popupMenu.setOnMenuItemClickListener(new
	 * PopupMenu.OnMenuItemClickListener() { public boolean
	 * onMenuItemClick(MenuItem item) { switch_btn.setText(item.getTitle());
	 * return true; } }); }
	 */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_switch:
			/*
			 * if (popupMenu != null) popupMenu.show(); // showing popup menu
			 * else initPopupMenu();
			 */
                if (popupWindow == null)
                    initPopWindow();
                int[] arrayOfInt = new int[2];
                view.getLocationOnScreen(arrayOfInt);
                int x = arrayOfInt[0];
                int y = arrayOfInt[1];
                System.out.println("x=" + x + ";" + "y=" + y);
                showPop(switch_btn, x, y + view.getHeight());
                break;
            case R.id.btn_add_group:
                Intent intent = new Intent(this, ReleaseGroupInfoAc.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void sendData(int type, Object data) {
        Intent intent = new Intent(this, SceneryDetailAc.class);
        intent.putExtra("type", type);

        switch (type) {
            case 1:
                intent.putExtra("obj", (ViewData) data);
                break;
            case 2:
                intent.putExtra("obj", (GroupItemData) data);
                break;
        }
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void onWidgetClick(int id, int flag) {
        switch (id) {
            case R.id.tv_traveller_center_uname:
                switch (flag) {
                    //登录或注册
                    case MeFragment.LOGIN_OR_REGISTER:
                        Intent intent = new Intent(this,LoginAc.class);
                        startActivity(intent);
                        break;
                    //认证
                    case MeFragment.USER_AUTH:
                        break;
                    //编辑
                    case MeFragment.USER_EDIT:
                        break;
                }
                break;
            case R.id.rl_order_manage:
                break;
            case R.id.rl_hand_in_hand:
                break;
            case R.id.rl_about_qianyouba:
                break;
            case R.id.rl_logout:
                break;
        }
    }
}
