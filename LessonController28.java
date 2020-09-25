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

	Random		rnd = new Random();		// 乱数オブジェクトの生成（種の生成）
	/**
	 * 50個目に生成された乱数を、バブルソート後にバイナリーサーチ(二分探索)をして見つけ出し探索結果を表示する
	 * @return
	 */
	@RequestMapping("/hijikata/ex028")
	public String binarysearch(){
		StringBuilder out = new StringBuilder();

			//配列 ＝ array
			int randomArray[] = new int[50];  //乱数を格納するための配列(50個分)
			final int randomMax = 1000;  //乱数の最大値(0-999) 定数 被らせない為乱数の幅を大きくしている

			out.append("↓乱数生成と格納");
			out.append("<br/>");


			//乱数を配列に格納する
			for (int i = 0;  i < randomArray.length; i ++ ) {
				int randomNumber;  //乱数値
				randomNumber = getRandom(randomMax);               //0～999までの乱数を発生

				randomArray[i] = randomNumber;
			}//乱数を配列に格納済み

			//格納されている数字を10個ずつ区切って表示するループ
			for (int i = 0;  i < randomArray.length; i ++ ) {
				out.append(randomArray[i]).append("　　　");//乱数の区切りのために空白を挿入する
				if(i == 9 || i == 19 || i == 29 || i == 39 || i == 49) {
					out.append("<br>");
				}
			}

			out.append("<br/>");
			out.append("<br/>");

			//二分探索で探索する数値の確認
			out.append("二分探索で探索する数値は").append(randomArray[49]);
			final int target = randomArray[49];  //この後並び替えが行われるため、ここで探索する数値を確定

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
			out.append("↓バブルソート実行後");
			out.append("<br/>");

			//格納されている数字を10個ずつ区切って表示するループ
			for (int i = 0;  i < randomArray.length; i ++ ) {
				out.append(randomArray[i]).append("　　　");//乱数の区切りのために空白を挿入する
				if(i == 9 || i == 19 || i == 29 || i == 39 || i == 49) {
					out.append("<br>");
				}
			}

			out.append("<br/>");
			out.append("<br/>");
			out.append("↓二分探索結果");
			out.append("<br/>");

		//ここに二分探索

			int low = 0;  //下限(初期はrandomArray[0]を指す)
			int high = randomArray.length;  //上限(初期はrandomArray.lengthを指す)
			int searchCount = 0;  //探索回数を示す数値;

			while(low <= high) {  //breakを踏むまで周回するループ(二分探索)
				int centerPoint = (low + high) / 2;  //下限と上限を足して2で割り、中心点を出す

				//中心点が探索する数値と一致した場合、探索回数を記録し終了
				if (randomArray[centerPoint] == target) {
					searchCount ++ ;  //探索回数を加算
					out.append("<br/>")
					.append("【探索完了】")
					.append("<br/>")
					.append("探索目標:").append(target).append("＝").append(centerPoint+1).append("番目")  //探索目標が何番目にあったのかを示す
					.append("<br/>")
					.append("探索回数は").append(searchCount).append("回です。");  //探索回数を示す
					break;  //breakを挟まないとwhile文の条件式に当てはまり続け終了しないため
				}

				//中心点より探索する数値が大きい場合
				else if(randomArray[centerPoint] < target) {
					searchCount ++ ;  //探索回数を加算
					out.append("中心点：").append(randomArray[centerPoint]).append("よりも数値が大きい").append("<br/>").append("<br/>");  //探索過程を示す
					low = centerPoint + 1;  //現在の中心点以下は探索対象が存在しないため探索範囲から外す
				}

				//中心点より探索する数値が小さい場合
				else{
					searchCount ++ ;  //探索回数を加算
					out.append("中心点：").append(randomArray[centerPoint]).append("よりも数値が小さい").append("<br/>").append("<br/>");  //探索過程を示す
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
		rundomNumber = rnd.nextInt(val);
		return rundomNumber;
	}
}
