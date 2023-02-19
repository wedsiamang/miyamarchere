package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CheckParameter {

	private ArrayList<String> _errs = null;

	public CheckParameter() {
		this._errs = new ArrayList<String>();
	}

	public void requiredCheck(String value, String name) {
		if (value == null) {
			Optional<String> opt = Optional.ofNullable(value);
			value = opt.orElse("");
			this._errs.add(name + "はnullです");
//			throw new IllegalArgumentException(name + "はnullは許容していません");
		} else if (value.equals("")) {
			this._errs.add(name + "は必須入力です");
		}
	}

	public void rangeCheck(int value, int min, int max, String name) {

		if (value < min || value > max) {
			this._errs.add(name + "は" + min + "以上、かつ" + max + "以下で入力してください");
		}
	}

	public void numCheck(String value, String name) {

		if (value == null) {
			// Optional<String> opt = Optional.ofNullable(value);
			// value = opt.orElse("");
			this._errs.add(name + "はnullです");
			throw new IllegalArgumentException(name + "はnullは許容していません");
		} else if (value.equals("")) {
			this._errs.add(name + "は必須入力です");
			// 半角数字１～５桁以外
		} else if (!value.matches("^\\d{1,5}$")) {
			this._errs.add(name + "は5桁以内の半角数字で入力してください");
		}
	}

	public void passCheck(String value, String name) {
		if (value.equals("")) {
			this._errs.add(name + "は必須入力です");

		} else if (!value.matches("^[a-zA-Z0-9!#$%&''()\"-^@[;:],./`{+*}\\<>?_]{5,10}+$")) {
			this._errs.add(name + value + "は受け付けられません。5文字から10文字以内の半角で入力してください");
		}

	}

	public void telNumberCheck(String value, String name) {

		if (value != null && value.equals("")) {
			this._errs.add(name + "は必須入力です");

		// 固定電話と携帯電話とIP番号とフリーダイヤル
		} else if (!value.matches("^0([0-9]-[0-9]{4}|[0-9]{2}-[0-9]{3}|[0-9]{3}-[0-9]{2}|[0-9]{4}-[0-9])-[0-9]{4}$|"
				+ "^0[5789]0-\\d{4}-\\d{4}$|^0120-\\d{3}-\\d{3}$")) {
			this._errs.add(name + value + "は受け付けられません。-（ハイフン）を入れて半角で入力してください");
		}
	}

	public void emailCheck(String value, String name) {
		if (value != null && value.equals("")) {
			this._errs.add(name + "は必須入力です");
		} else if (!value.matches(
				"^[a-zA-Z0-9_+-]+(.[a-zA-Z0-9_+-]+)*@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,}$")) {
			this._errs.add(value + name + "は受け付けられません。半角で入力してください");
		}
	}

	public void lengthCheck(String value, String name) {

		if (!value.matches("[!-~]{5,10}")) {
			this._errs.add(name + "は5文字以上10文字以下で入力してください");
		}
	}

	public boolean hasErrors() {
		return !this._errs.isEmpty();
	}

	public List<String> getError() {
		return this._errs;
	}
}
