// IAddition.aidl
package com.example.bosch2;

/*
1.  i added aidl in build features of gradle = true sync
2. select the app folder rt click -- new - aidl-aidl file
3. gavethe name as IAddMoto and create a abstract method add(int a, int b)
4. rebuild the project
5.  in MyService -- create a var aidlBinder and implement the add method
5. in manifest add the intent filter
 */


interface IAddition {
  int sumAdd(int a, int b);
}