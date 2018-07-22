#!/bin/sh
# POSTs transactions infinitely
# Tested only on Cygwin and not very efficient
amount=0
while [ 0 ]; do
  date=$((`TZ="UTC" date +%s` * 1000 ))
  echo '{"amount": '$amount',"timestamp": '$date'}' | curl -s -d @- --header "Content-Type:application/json" localhost:8080/transactions
  amount=$((amount + 1));
done