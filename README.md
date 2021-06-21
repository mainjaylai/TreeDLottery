# 3D福彩
## 一、设计思路
1. 定义 Sale_Store 类，其中类成员分别是可以购买的 CD 数 和可以租售的 CD 序列等等，分别用 int 数组保存数量和 boolean 数 组来 RentCD 保存状态，而且全部为 static 类型（这样是为了是所有 顾客购买和租售的 CD 数目是时刻保持一致的）。并在类中定义一系 列租售 CD，购买 CD 和补充可销售 CD 的方法，并且其中方法均为 synchronized，保证该进程永远都是同步的。 
2. 定义 RentCD 类，实现 Runable 接口。首先，定义一个顾 客的唯一的 name 属性，方便我们后期不同观察顾客的行动。其次是 重写 run 方法中的进程，随机数产生租用 CD 的种类，然后判断该种 类的 CD 是否被其他租客租用。若被租客租用，则将随机判断是否等 待或者放弃（这里我是用随机数模 2 运算），如果继续等待，则进行 while 循环，接着判断该 CD 是否被租用；如果选择放弃，则 continue， 跳过所有步骤；若是该种类的 CD 没有被租用，则该租客可以租用 CD。并在 file 文件中写入相关的内容。
3. 定义 BuyCD 类，实现 Runable 接口。像之前 RentCD 类一 样。首先，定义一个顾客的唯一的 name 属性，也是方便我们后期不 同观察顾客的行动。其次是重写 run 方法中的进程，随机数产生购买 CD 的种类和数量，然后判断该种类的 CD 是否有剩余。若被商店中 CD 的数目不够，则将随机判断是否等待或者放弃（这里我同时也是
用随机数模 2 运算），如果选择继续等待，则紧急调用补货函数进行 补货；如果选择放弃，则 continue，跳过所有步骤。若是该种类的 CD 还有剩余，则该租客可以购买 CD。并在 file 文件中写入相关的内 容。④
4. 定义 ProduceCD 类，实现 Runable 接口，重写 run 函数， 每睡眠 1000ms 则调用 Sale_Store 类中的补货函数。 
5. 定义 FileWrite 类，此为文件写入类，新建 record 文件，并 创建相关的文件输入流，向文件中写入相关的信息。
## 二、程序输出相关结果 
### 1．租客和购买顾客的结果
![image](https://user-images.githubusercontent.com/73374331/122762173-9da79180-d2cf-11eb-82b4-98e866a8ea89.png)
### 2．若是想买光盘但是数量不够，放弃等待 
![image](https://user-images.githubusercontent.com/73374331/122762223-ac8e4400-d2cf-11eb-90bc-0e1e89b3c721.png)
### 3．若是想买光盘但是数量不够，紧急补货
![image](https://user-images.githubusercontent.com/73374331/122762252-b3b55200-d2cf-11eb-8b3f-576067cf148f.png)
### 4．若是想租用光盘但是已经被租，放弃等待 
![image](https://user-images.githubusercontent.com/73374331/122762275-bc0d8d00-d2cf-11eb-982e-219d1e9ec3cb.png)
### 5．若是想租用光盘但是已经被租，等待别人租完 
![image](https://user-images.githubusercontent.com/73374331/122762309-c3cd3180-d2cf-11eb-8c5e-58150277832d.png)
![image](https://user-images.githubusercontent.com/73374331/122762322-c891e580-d2cf-11eb-95fe-6b62856d7a3b.png)
![image](https://user-images.githubusercontent.com/73374331/122762329-cb8cd600-d2cf-11eb-89cb-d34c2e8889ef.png)

## 三、关键代码图
![image](https://user-images.githubusercontent.com/73374331/122762362-d34c7a80-d2cf-11eb-95bd-4eac8806d472.png)

## 四、源代码 
[源代码](https://github.com/lmj2001/TreeDLottery)
