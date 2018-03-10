# 你能学到的知识点
![项目目录结构](http://p2je16s75.bkt.clouddn.com/project.png)
1. 一个Activty,多个Fragment
```
1) 封装BaseDelegate的抽象Fragment基类
2) 继承SwipeBackFragment ,通过 setLayout 指定布局资源,抽取抽象onBindView方法,强制子类绑定子布局
3) Delegate状态保存/现场恢复
4) 避免错误操作导致Delegate的视图重叠
5) 避免异步操作导致Delegate崩溃
6) Delegate 里监听虚拟按键和实体按键的返回事件
7) 使用最大化的DialogFragment来实现浮动层级视图
8) 判断一个页面该使用Delegate还是Activty
```
![Fragment基类](http://p5do3ypyn.bkt.clouddn.com/BaseDelegate.png)
2. 单个Activity界面架构认证
```
1) 封装ProxyActivity的抽象Activity基类
2) 为每一个子视图设置唯一的resource id, 开源库fragmentation 里面的SupportActivity自带加载LoadRootFrgment方法,为每一个自视图加载Fragment
3) 每块子Delegate强制继承成LateDelegate,而最终结果引导给ProxyActivty的抽象方法
```

![Activity基类](http://p5do3ypyn.bkt.clouddn.com/ProxyActivity.png)
3. 网络接口的创建
4. RestFul请求处理
5. 拦截器功能设计与实现
6. Retrofit与RxJava的整合，实现网络请求的响应式编程
7. 启动页开发

# 主流框架

- fastjson
- greendao
- picasso
- okio
- retrofit2
- recyclerview
- rxandroid
- iconify
