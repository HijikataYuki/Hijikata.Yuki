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
 * @author hijikata.yuki
 *
 */
@SpringBootApplication
@RestController
public class LessonController27 {

	Random		rnd = new Random();		// 乱数オブジェクトの生成（種の生成）
	@RequestMapping("/hijikata/ex027")
	public String main(){

		StringBuilder out = new StringBuilder();

		int array[]= new int[10];
		int a = 100;             // 乱数生成用
		int	b;						// 乱数値
		int box = 0;                         // 仮格納用


		out.append("↓乱数生成と格納");
		out.append("<br/>");


		//乱数を配列に格納
		for (int i=0;  i<array.length; i++) {
			b = subMethod(a);               // 0～99までの乱数を発生
			out.append(b + "　　　" );
			array[i]=b;
		}

		out.append("<br/>");
		out.append("<br/>");


		//ソートが完了するまでのループ

		//2以上の間は実行する、1以下で終了
		for (int fin=array.length; fin>=2; fin--) {

			//すべての数字を回るループ
			for (int i=1; i<array.length; i++) {

				//前の数字と比較して並び替える判定
				if (array[i-1] > array[i]) {
					box = array[i]; //手前の数字を箱へ格納
					array[i] = array[i-1];  //奥の数字を手前へ
					array[i-1] = box;  //箱から奥の数字へ元・手前の数字を格納(入れ替え完了)
				}
			}

		}

		//バブルソート完了
		out.append("<br/>");
		out.append("↓バブルソート実行後");
		out.append("<br/>");
		for (int i=0; i<array.length; i++) {
			out.append(array[i] + "　　　" );
		}
		return out.toString();
	}

	//乱数生成用メソッド
	public int subMethod(int val) {
		int c=val;
		c = rnd.nextInt(val);
		return c;
	}

}