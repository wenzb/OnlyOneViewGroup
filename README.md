# OnlyOneViewGroup
一组View只显示其中一个，用于类似注册等下一步切换UI功能，且不新建Activity。

</br>

<img src="https://github.com/wenzb/OnlyOneViewGroup/blob/master/app/src/main/res/raw/test.gif" width="266px" width="484px" />
</br>


## 使用步骤：

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

dependencies {
	compile 'com.github.wenzb:OnlyOneViewGroup:1.0.1'
	}
	
```
     <com.wzb.onlyoneviewgroup.OnlyOneViewGroup
        android:id="@+id/test_only_one_view_group"
        android:layout_width="match_parent"
        android:layout_height="400dp">

        <TextView
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:layout_gravity="center_horizontal"
            android:background="#182ef2"
            android:gravity="center"
            android:text="布局文件载入1"
            android:textSize="20sp" />

        <TextView
            android:layout_width="200dp"
            android:layout_height="200dp"
            android:layout_gravity="center_horizontal"
            android:background="#f2bf18"
            android:gravity="center"
            android:text="布局文件载入2"
            android:textSize="20sp" />

    </com.wzb.onlyoneviewgroup.OnlyOneViewGroup>
```

OnlyOneViewGroup mOnlyOneViewGroup=new OnlyOneViewGroup(context ,layouts);


优先显示xml文件子View通过 mOnlyOneViewGroup.addLayoutRes(int [] layouts)添加; layouts资源布局文件

压入返回栈:
mOnlyOneViewGroup.setAddToBackTask(true);

获取子View控件实例: 
tvLayout2=mOnlyOneViewGroup.findViewById(R.id.test_layout2_tv);

```
    /**
     * 添加布局文件到OnlyOneViewGroup
     */
    public void addLayoutRes(int[] layouts)

    /**
     * 添加布局文件到OnlyOneViewGroup
     */
    public void addLayoutRes(@LayoutRes int layoutId)

    /**
     * 清除所有View
     */
    public void clearViews()

    /**
     * 显示指定index
     */
    public void showIndexView(int index)

    /**
     * 显示下一个View
     */
    public void showNext()
    
    /**
     * 显示上一个View
     */
     public void showLast()
     
    /**
     * 显示上一个View 第0个时候显示最后一个
     */
    public void showLastCirculation()

    /**
     * 在最后一个时显示第0个
     */
    public void showNextCirculation()

    /**
     * 是否压入返回栈
     */
    public boolean isAddToBackTask()

    /**
     * 设置压入返回栈
     */
    public void setAddToBackTask(boolean addToBackTask)
```


    更多使用请看例子
