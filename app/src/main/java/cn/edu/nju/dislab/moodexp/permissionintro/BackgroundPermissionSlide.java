package cn.edu.nju.dislab.moodexp.permissionintro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import agency.tango.materialintroscreen.SlideFragment;
import cn.edu.nju.dislab.moodexp.R;

import static cn.edu.nju.dislab.moodexp.permissionintro.IntentWrapper.*;

/**
 * Created by zhantong on 2016/12/24.
 */

public class BackgroundPermissionSlide extends SlideFragment{
    //private boolean canMoveFurther=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.background_permission_slide,container,false);
        Button button=(Button)view.findViewById(R.id.button_permission_slide);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whiteListCheck();
                //canMoveFurther=true;
            }
        });
        return view;
    }

/*    @Override
    public boolean canMoveFurther() {
        return canMoveFurther;
    }*/
    @Override
    public String cantMoveFurtherErrorMessage() {
        return "请点击按钮";
    }
    @Override
    public int backgroundColor() {
        return R.color.colorPrimary;
    }

    @Override
    public int buttonsColor() {
        return R.color.colorAccent;
    }

    private void whiteListCheck(){
        boolean nothingMatches = true;
        Context context=getContext();
        final Activity activity=getActivity();
        for (IntentWrapper intentWrapperNonFinal : sIntentWrapperList) {
            //如果本机上没有能处理这个Intent的Activity，说明不是对应的机型，直接忽略进入下一次循环。
            final IntentWrapper intentWrapper=intentWrapperNonFinal;
            if (!intentWrapper.doesActivityExists()) continue;
            switch (intentWrapper.mType) {
                case DOZE:
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                        PowerManager pm = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
                        if (pm.isIgnoringBatteryOptimizations(getContext().getPackageName())) break;
                        nothingMatches = false;
                        new AlertDialog.Builder(context)
                                .setCancelable(true)
                                .setTitle("需要忽略 " + getApplicationName() + " 的电池优化")
                                .setMessage("服务的持续运行需要 " + getApplicationName() + " 加入到电池优化的忽略名单。\n\n" +
                                        "请点击『确定』，在弹出的『忽略电池优化』对话框中，选择『是』。")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        intentWrapper.startActivity(activity);
                                    }
                                })
                                .setNegativeButton("取消",null)
                                .show();
                    }
                    break;
                case HUAWEI:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 自动启动")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 的后台自动启动。\n\n" +
                                    "请点击『确定』，在弹出的『自动启动管理』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case ZTE_GOD:
                case HUAWEI_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("" + getApplicationName() + " 需要加入受保护的应用名单")
                            .setMessage("服务的持续运行需要 " + getApplicationName() + " 加入到受保护的应用名单。\n\n" +
                                    "请点击『确定』，在弹出的『受保护应用』列表中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case XIAOMI_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要关闭 " + getApplicationName() + " 的神隐模式")
                            .setMessage("服务的持续运行需要 " + getApplicationName() + " 的神隐模式关闭。\n\n" +
                                    "请点击『确定』，在弹出的神隐模式应用列表中，点击 " + getApplicationName() + " ，然后选择『无限制』和『允许定位』。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case SAMSUNG:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage("服务的持续运行需要 " + getApplicationName() + " 在屏幕关闭时继续运行。\n\n" +
                                    "请点击『确定』，在弹出的『智能管理器』中，点击『内存』，选择『自启动应用程序』选项卡，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case MEIZU:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 的自启动。\n\n" +
                                    "请点击『确定』，在弹出的应用信息界面中，将『自启动』开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case MEIZU_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("" + getApplicationName() + " 需要在待机时保持运行")
                            .setMessage("服务的持续运行需要 " + getApplicationName() + " 在待机时保持运行。\n\n" +
                                    "请点击『确定』，在弹出的『待机耗电管理』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case ZTE:
                case LETV:
                case XIAOMI:
                case OPPO:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage("服务的持续运行需要 " + getApplicationName() + " 加入到自启动白名单。\n\n" +
                                    "请点击『确定』，在弹出的『自启动管理』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case OPPO_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 在后台运行")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 在后台运行。\n\n" +
                                    "请点击『确定』，在弹出的『纯净后台应用管控』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case VIVO:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 的自启动。\n\n" +
                                    "请点击『确定』，在弹出的 i管家 中，找到『软件管理』->『自启动管理』，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case COOLPAD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 的自启动")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 的自启动。\n\n" +
                                    "请点击『确定』，在弹出的『酷管家』中，找到『软件管理』->『自启动管理』，取消勾选 " + getApplicationName() + "，将 " + getApplicationName() + " 的状态改为『已允许』。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case VIVO_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("" + getApplicationName() + " 需要在后台高耗电时允许运行")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 在后台高耗电时运行。\n\n" +
                                    "请点击『确定』，在弹出的『后台高耗电』中，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case GIONEE:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("" + getApplicationName() + " 需要加入应用自启和绿色后台白名单")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 的自启动和后台运行。\n\n" +
                                    "请点击『确定』，在弹出的『系统管家』中，分别找到『应用管理』->『应用自启』和『绿色后台』->『清理白名单』，将 " + getApplicationName() + " 添加到白名单。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case LETV_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要禁止 " + getApplicationName() + " 被自动清理")
                            .setMessage("服务的持续运行需要禁止 " + getApplicationName() + " 被自动清理。\n\n" +
                                    "请点击『确定』，在弹出的『应用保护』中，将 " + getApplicationName() + " 对应的开关关闭。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case LENOVO:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要允许 " + getApplicationName() + " 的后台 GPS 和后台运行")
                            .setMessage("服务的持续运行需要允许 " + getApplicationName() + " 的后台自启、后台 GPS 和后台运行。\n\n" +
                                    "请点击『确定』，在弹出的『后台管理』中，分别找到『后台自启』、『后台 GPS』和『后台运行』，将 " + getApplicationName() + " 对应的开关打开。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case LENOVO_GOD:
                    nothingMatches = false;
                    new AlertDialog.Builder(context)
                            .setCancelable(true)
                            .setTitle("需要关闭 " + getApplicationName() + " 的后台耗电优化")
                            .setMessage("服务的持续运行需要关闭 " + getApplicationName() + " 的后台耗电优化。\n\n" +
                                    "请点击『确定』，在弹出的『后台耗电优化』中，将 " + getApplicationName() + " 对应的开关关闭。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    intentWrapper.startActivity(activity);
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
            }
        }
        if (nothingMatches) Toast.makeText(context, "不是对应的机型", Toast.LENGTH_SHORT).show();
    }

}
