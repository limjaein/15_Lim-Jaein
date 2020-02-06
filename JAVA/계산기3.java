package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 계산기3 {
	static final int T = 10;
	static Stack<Integer> num = new Stack<>();
	static Stack<Character> op = new Stack<>();
	//static StringBuilder postfix = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		char c;
		int len;
		
		for (int t = 0; t < T; t++) {
			len = Integer.parseInt(in.readLine());
			input = in.readLine();
			// 후위 표기법 변환
			for (int i = 0; i < len; i++) { 
				c = input.charAt(i);
				if (c == '(') {
					op.push(c);
				} else if (c == ')') {
					// pop
					while(op.peek() != '(')
						num.push(cal());
					op.pop();
				} else {
					if (c >= '0' && c <= '9') { // 숫자
						//postfix.append(c);
						num.add(Integer.parseInt(c + ""));
					} else { // 문자
						if(c == '+' && op.size()>0) {
							while(op.peek() != '(')
								num.push(cal());
						}
						op.push(c);
					}
				}
			}
			while(op.size()>0) {
				if(op.peek() != '(') {
					num.push(cal());
				}else
					op.pop();
			}
			//System.out.println("postfix : "+postfix.toString());
			System.out.println("#"+(t+1)+" "+num.pop());
		}
	}

	private static int cal() {
		int l, r;
		char o;
		r = num.pop();
		l = num.pop();
		o = op.pop();
		//postfix.append(o);
		
		switch (o) {
		case '+':
			return l+r;
		case '*':
			return l*r;
		}
		return 0;
	}

}
