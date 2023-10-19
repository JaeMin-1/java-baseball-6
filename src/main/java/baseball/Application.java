package baseball;

import java.io.*;
import java.util.*;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("숫자 야구 게임을 시작합니다.");
        while(true) {
            List<Integer> answer = AnswerGenerator.generateAnswer();

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
                * 모두 1 ~ 9 사이 숫자인지 판단
                */
                for(int i=0; i<3; i++) {
                    char c = input.charAt(i);
                    if(!Character.isDigit(c) || Character.getNumericValue(c) == 0)
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

                /*
                * 볼, 스트라이크 판단 여부 출력
                */
                if (result[0] == 0 && result[1] == 0)
                    System.out.println("낫싱");
                else {

                }

                if(result[1] == 3) {
                    System.out.println("3스트라이크");
                    System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                    System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

                    /*
                    * 게임 재시작 및 종료
                    */
                    String game = Console.readLine();
                    if(game.equals("1"))
                        break;
                    if(game.equals("2"))
                        return;
                }

                String output = "";
                if (result[0] > 0)
                    output += result[0] + "볼 ";
                if (result[1] > 0)
                    output += result[1] + "스트라이크";
                if(output.length() != 0)
                    System.out.println(output);

            }

        }

    }
}
