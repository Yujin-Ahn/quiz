/**
 * @author Yujin Ahn
 */

package com.apply.test;

import java.util.*;

public class ApplyTest {
  public static void main(String[] args) {
    System.out.println("0 ~ 9 까지의 자연수를 입력하세요.");
    System.out.println("같은 숫자는 2개까지만 유효합니다.");
    System.out.println("숫자는 총 2개 이상 18개 이하만 입력할 수 있습니다.");
    System.out.println("범위를 벗어난 숫자를 입력하면 숫자입력이 종료됩니다. (ex) 9보다 큰 수");

    Scanner sc = new Scanner(System.in);
    List<Integer> al = new ArrayList<Integer>();
    char str = ' ';
    while (str != 'y' && str != 'Y') {
      al.clear(); // 리스트 초기화
      while (al.size() < 18) {      // 숫자입력은 18개까지 받기
        int num = sc.nextInt();
        if (num >= 0 && num <= 9) { // 자연수의 범위는 0 ~ 9
          int cnt = 0;
          if (al.size() == 0)
            al.add(num);
          else {
            for (int i = 0; i < al.size(); i++) { 
              if (num == al.get(i))              
                cnt++;
            }
            if (cnt < 2) al.add(num); // 같은 수를 세 번 이상 입력할 경우 입력이 무시됨        
          }
        }
        else if (al.size() >= 2) { // 숫자는 두 개 이상 입력해야 끝남
          break;                   // 두 개 이상일 때 범위 벗어난 숫자 입력시 입력 종료
        }
      } // 숫자입력 while문 종료
      System.out.print(al + " -> "); // 입력한 숫자 표시
      Collections.sort(al);          // 오름차순 정렬

      // 입력받은 숫자의 개수를 나눠서 두 자연수의 자릿수를 정함
      int n1, n2;
      n2 = al.size() / 2; 
      n1 = (al.size() % 2 == 0) ? n2 : (n2 + 1);

      char[] num1 = new char[n1];
      char[] num2 = new char[n2];

      int i = 0;
      int result = 0;
      while (al.size() > 0) {
        int j = 0;
        // 맨 앞자리에 0이 오지 못하게 하려함
        if (i == 0 && al.get(j) == 0) {
          while (al.get(j) == 0) {
            j++;
            if (j >= al.size()) 
              break;
          }
        }
        // 0만 입력 받은 경우
        if (j == al.size() && j!=0) {
          num1[i]=num2[i]='0'; 
          break;
        }
        // 그 외에는 배열에 순서대로 숫자를 채워넣고 리스트에서 삭제함
        num1[i] = al.get(j).toString().charAt(0);
        al.remove(j);

        if (i < num2.length && al.size() > j ) {
          num2[i] = al.get(j).toString().charAt(0);
          al.remove(j);
        }
        // 자연수가 두 개가 나올 수 없는 경우
        if (al.size() <= j && al.size() != 0) {
          result = -1;
          break;
        }
        i++;
      } // 두 자연수를 만드는 while문 종료
      
      String newNum1 = new String(num1);
      String newNum2 = new String(num2);

      // 두 자연수가 만들어진 경우에 합을 계산
      if (result != -1)
        result = Integer.parseInt(newNum1) + Integer.parseInt(newNum2);
      System.out.println(result); // 결과를 출력
      
      System.out.print("끝내려면 'y' 계속하려면 아무키나 누르세요. ");
      str = sc.next().charAt(0);
      System.out.println("-----");
    } // 전체과정을 반복하는 while문 종료
  } // end of main()
} // end of class

/* OUTPUT 
0 ~ 9 까지의 자연수를 입력하세요.
같은 숫자는 2개까지만 유효합니다.
숫자는 총 2개 이상 18개 이하만 입력할 수 있습니다.
범위를 벗어난 숫자를 입력하면 숫자입력이 종료됩니다. (ex) 9보다 큰 수
1 2 3 1 2 3 33
[1, 2, 3, 1, 2, 3] -> 246
끝내려면 'y' 계속하려면 아무키나 누르세요. k
-----
0 0 1 22
[0, 0, 1] -> -1
끝내려면 'y' 계속하려면 아무키나 누르세요. y
-----
*/
