/**
 * LessonController.java
 *
 * 2020/08/26 hijikata.yuki
 *
 * Copyright (c)2020, ADMAX Corporation. All rights reserved.
 */
package com.example.demo.controller;

import java.util.Random;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演習27：バブルソート
 * @author hijikata.yuki
 */
@SpringBootApplication
@RestController
public class LessonController27 {

	Random rnd = new Random();// 乱数オブジェクトの生成（種の生成）
	/**
	 * バブルソートをする
	 * @return
	 */
	@RequestMapping("/hijikata/ex027")
	public String doBubbleSort(){

		StringBuilder out = new StringBuilder();
		//配列 ＝ array
		int randomArray[] = new int[10];  //乱数を格納するための配列(10個分)
		final int randomMax = 100;  //乱数の最大値(0-99) 定数

		out.append("↓乱数生成と格納");
		out.append("<br/>");


		//乱数を配列に格納する
		for (int i = 0;  i < randomArray.length; i ++ ) {
			int randomNumber;  //乱数値
			randomNumber = getRandom(randomMax);               //0～99までの乱数を発生
			out.append(randomNumber).append("　　　");  //乱数の区切りのために空白を挿入する
			randomArray[i] = randomNumber;
		}//乱数を配列に格納済み

		out.append("<br/>");
		out.append("<br/>");


		//ソートが完了するまでのループ(大ループ)

		//配列最大値-1回繰り返す(一回につき一つの数字の位置が確定する)
		for (int j = 0; j < randomArray.length - 1; j ++ ) {

			//前後の数字を比較するループを行う(小ループ)
			//(配列最大値-1-大ループの回数)回繰り返す(大ループ一回につき数字の配置が一つ確定しているから)
			for (int i = 0; i < randomArray.length - 1 - j; i ++ ) {

				//配列の前後の要素と比較して前が大きい場合は入れ替える
				if ( randomArray[i] > randomArray[i + 1] ) {
					int temp;  //仮格納用の変数を宣言することで入れ替える際に使用する
					temp = randomArray[i] ;  //宣言した仮格納用の変数に一時的に前の数字を格納する
					randomArray[i] = randomArray[i + 1];  //前の数字が格納されている箇所に後の数字を格納(前方へ後の数字を移動させたことになる)
					randomArray[i + 1] = temp;  //後の数字が格納されている箇所に、仮格納用の変数に一時的に格納されている前の数字を格納する(後方へ前の数字を移動させたことになる)
				}
			}

		}

		//バブルソート完了後の配列の中身を表示
		out.append("<br/>");
		out.append("↓バブルソート実行後");
		out.append("<br/>");
		for (int i = 0; i < randomArray.length; i ++ ) {
			out.append(randomArray[i]).append("　　　");//乱数の区切りのために空白を挿入する
		}
		return out.toString();
	}

	//乱数生成用メソッド
	/**
	 * 乱数生成用メソッド
	 * @param val 乱数の最大値
	 * @return 乱数
	 */
	public int getRandom(int val) {
		int rundomNumber;
		rundomNumber = rnd.nextInt(val);
		return rundomNumber;
	}

}