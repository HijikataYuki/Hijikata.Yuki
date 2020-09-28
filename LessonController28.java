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
 * 演習28：二分探索
 * @author hijikata.yuki
 *
 */
@SpringBootApplication
@RestController
public class LessonController28 {

	Random rnd = new Random(); // 乱数オブジェクトの生成（種の生成）
	final static String  br = "<br/>"; //改行出力の簡略化

	/**
	 * 50個目に生成された乱数を、バブルソート後にバイナリーサーチ(二分探索)をして探索結果を表示する
	 * @return
	 */
	@RequestMapping("/hijikata/ex028")
	public String binarysearch(){

		StringBuilder out = new StringBuilder();
		int randomArray[] = new int[50];  //乱数を格納するための配列(50個分)
		final int randomMax = 900;  //乱数の最大値(0-899) 定数

		out.append("↓乱数生成と格納");
		out.append(br);

		//乱数を配列に格納する
		for (int i = 0;  i < randomArray.length; i ++ ) {
			int randomNumber;  //乱数値
			randomNumber = getRandom(randomMax); //0～999までの乱数を発生
			randomArray[i] = randomNumber;
		}//乱数を配列に格納済み

		//格納されている数字を10個ずつ区切って表示するループ
		String dispData;
		dispData = dispData(randomArray);
		out.append(dispData);

		out.append(br);
		out.append(br);

		//二分探索で探索する数値の確認
		out.append("二分探索で探索する数値は").append(randomArray[49]);
		final int target = randomArray[49];  //この後並び替えが行われるため、ここで探索する数値を確定

		out.append(br);
		out.append(br);

		//ソートが完了するまでのループ(大ループ)
		//配列最大値-1回繰り返す(一回につき一つの数字の位置が確定する)
		for (int j = 0; j < randomArray.length - 1; j ++ ) {

			//前後の数字を比較するループを行う(小ループ)
			//(配列最大値-1-大ループの回数)回繰り返す(大ループ一回につき数字の配置が一つ確定)
			for (int i = 0; i < randomArray.length - 1 - j; i ++ ) {

				//配列の前後の要素と比較して前が大きい場合は入れ替える
				if ( randomArray[i] > randomArray[i + 1] ) {
					int temp;  //仮格納用の変数を宣言
					temp = randomArray[i] ;  //変数に一時的に前の数字を格納する
					randomArray[i] = randomArray[i + 1];  //前方へ後の数字を移動
					randomArray[i + 1] = temp;  //後方へ前の数字を移動
				}
			}
		}

		//バブルソート完了後の配列の中身を表示
		out.append("↓バブルソート実行後");
		out.append(br);

		//格納されている数字を10個ずつ区切って表示するループ
		dispData = dispData(randomArray);
		out.append(dispData);

		out.append(br);
		out.append(br);
		out.append("↓二分探索結果");
		out.append(br);

		int low = 0;  //下限
		int high = randomArray.length;  //上限
		int searchCount = 0;  //探索回数を示す数値;

		while(true) {  //breakを踏むまで周回する(二分探索)

			int centerPoint = (low + high) / 2;  //下限と上限を足して2で割り、中心点を出す
			searchCount ++ ;  //探索回数を加算

			//中心点が探索する数値と一致した場合、探索回数を記録し終了
			if (randomArray[centerPoint] == target) {
				out.append("<br/>")
				.append("【探索完了】")
				.append("<br/>")
				.append("探索目標:").append(target)
				.append("＝").append(centerPoint + 1).append("番目")  //探索目標が何番目にあったかを示す
				.append("<br/>")
				.append("探索回数は").append(searchCount).append("回です。");  //探索回数を示す
				break;
			}

			//中心点より探索する数値が大きい場合
			else if(randomArray[centerPoint] < target) {
				out.append("中心点：").append(randomArray[centerPoint])
				.append("よりも数値が大きい").append("<br/>").append("<br/>");  //探索過程を示す
				low = centerPoint + 1;  //現在の中心点以下は探索対象が存在しないため探索範囲から外す
			}

			//中心点より探索する数値が小さい場合
			else{
				out.append("中心点：").append(randomArray[centerPoint])
				.append("よりも数値が小さい").append("<br/>").append("<br/>");  //探索過程を示す
				high = centerPoint - 1;  //現在の中心点以上は探索対象が存在しないため探索範囲から外す
			}

		}

	return out.toString();
	}

	/**
 	* 乱数生成用メソッド
	* @param val 乱数の最大値
	* @return 乱数
	*/
	public int getRandom(int val) {
		int rundomNumber;
		rundomNumber = rnd.nextInt(val) + 100;
		return rundomNumber;
	}

	/**
	 * 配列内表示メソッド
	 * @param 表示したい配列
	 * @return 10要素ごとに改行して表示する文字列
	 */
	public String dispData(int[] array) {
		StringBuilder returnData = new StringBuilder();
		for(int i = 0;i < array.length;i ++ ) {
			returnData.append(array[i]).append("　　　"); //乱数の区切りのために空白を挿入する
			if(i % 10 == 9) { //(10の倍数)番目で改行を挟む
				returnData.append("<br />");
			}
		}
		return returnData.toString();
	}

}
