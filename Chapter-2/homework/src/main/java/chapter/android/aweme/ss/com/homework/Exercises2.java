package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 * @author lisongyang
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public int getAllChildViewCount(View view) {
        //初始化变量
        int addition = 0;  //子树上的增量（为了不影响循环控制而单独设立）
        int count = 0;    //子代的数量 （要控制循环）
        ViewGroup viewGroup = null; //预备的viewGroup，带入view若是则复制过去

        //先判断view是否是ViewGroup
        if(view instanceof ViewGroup){
            addition = 1;
        }else{
            viewGroup = (ViewGroup)view;
            count = viewGroup.getChildCount();
        }

        //若是ViewGroup则开始计数
        for(int i=0;i<count;count++){
            View viewChild = viewGroup.getChildAt(i);
            if(viewChild instanceof ViewGroup){
                //子树也是则递归计算子树上的节点数
                addition += getAllChildViewCount((ViewGroup)viewChild);
            }else{
                addition += 1;
            }
        }
        return count+addition;
    }
}
