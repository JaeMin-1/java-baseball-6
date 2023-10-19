package baseball;

import java.io.*;
import java.util.*;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        while(true) {
            /*
            * 정답 생성
            * 중복되지 않은 세개의 숫자
            * 1 ~ 9 범위에 해당되는 숫자
            */
            List<Integer> answer = new ArrayList<>();
            while (answer.size() < 3) {
                int randomNumber = Randoms.pickNumberInRange(1, 9);
                if (!answer.contains(randomNumber)) {
                    answer.add(randomNumber);
                }
            }

            System.out.println("숫자 야구 게임을 시작합니다.");
            int[] guess = new int[3];
            while(true) {
                System.out.print("숫자를 입력해주세요 : ");
                String input = Console.readLine();
                /*
                * 세자리인지 판단
                */
                if(input.length()!=3)
                    throw new IllegalArgumentException();
                /*
                * 모두 숫자인지 판단
                */
                for(int i=0; i<3; i++) {
                    char c = input.charAt(i);
                    if(!Character.isDigit(c))
                        throw new IllegalArgumentException();
                    guess[i] = Character.getNumericValue(c);
                }
                /*
                * 중복 숫자가 있는지 판단
                */
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        if(i!=j && guess[i] == guess[j])
                            throw new IllegalArgumentException();
                    }
                }
                /*
                * 스트라이크, 볼 여부를 판단
                */
                int[] result = new int[2]; //ball, strike 순서
                for(int i=0; i<3; i++) {
                    if(guess[i] == answer.get(i)) result[1]++;
                    else {
                        for(int j=0; j<3; j++) {
                            if(guess[i] == answer.get(j))
                                result[0]++;
                        }
                    }
                }

            }

        }

    }
}
