package com.tkxwebs.crypto.sample;

import org.apache.commons.lang3.RandomStringUtils;

import com.tkxwebs.crypto.Crypto;
import com.tkxwebs.crypto.util.FileUtil;

public class CryptoExample {
	public static void main(String args[]) {
		/*******************************************************************************
		 * 暗号化と復号化は、別々に行われるのが普通であるので、暗号解読キーと、IVは、ファイル
		 * に保存しておき、実行時に読み込んで利用する
		 *******************************************************************************/

		/*------------------------------------------------------------------------------
		   データの準備
		--------------------------------------------------------------------------------*/
		byte[] iv = RandomStringUtils.randomAlphanumeric(16).getBytes();// IV(暗号化時のスタートブロック用の初期値 128ビット固定長）
		byte[] key = "kawaba_2015_key_".getBytes(); // 暗号解読キー(128ビット固定長)

		/*------------------------------------------------------------------------------
		   IVと暗号解読キーはファイルに保存しておく
		--------------------------------------------------------------------------------*/
		FileUtil.writeBytes(iv, "iv");
		FileUtil.writeBytes(key, "secret");

		/*------------------------------------------------------------------------------
		   データの表示
		--------------------------------------------------------------------------------*/
		System.out.println("IV(スタートブロック用の初期値）=" + new String(iv) + "（" + iv.length + "byte）");
		System.out.println("暗号解読キー＝" + new String(key) + "（16byte）");

		/*------------------------------------------------------------------------------
		   暗号化の処理
		--------------------------------------------------------------------------------*/
		String source = "なんたらかんたら"; // 暗号化する文字列
		String result = ""; // 暗号化した結果の文字列
		try { // Cryptoオブジェクトを生成する
			Crypto c = new Crypto(FileUtil.readBytes("secret"), FileUtil.readBytes("iv")); // 解読キーとIVをファイルから読み込む
			result = c.encrypto(source); // 暗号化した文字列を得る
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("暗号＝" + result); // 暗号文字列を表示する

		/*------------------------------------------------------------------------------
		   復号化の処理
		--------------------------------------------------------------------------------*/
		try { // Cryptoオブジェクトを生成する
			Crypto c2 = new Crypto(FileUtil.readBytes("secret"), FileUtil.readBytes("iv")); // 解読キーとIVをファイルから読み込む
			result = c2.decrypto(result); // 復号化した文字列を得る
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("復号＝" + result); // 元の文字列を表示する
	}
}