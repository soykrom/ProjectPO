#!/bin/bash
#export CVSROOT=:ext:ist192494@sigma.ist.utl.pt:/afs/ist.utl.pt/groups/leic-po/po19/cvs/080
let total=0;
let correct=0;
export CLASSPATH=./m19-app/m19-app.jar:./m19-core/m19-core.jar:./po-uuilib/po-uuilib.jar:.
for x in tests-ei/*.in; do
    if [ -e ${x%.in}.import ]; then
        java -Dimport=${x%.in}.import -Din=$x -Dout=${x%.in}.outhyp m19.app.App;
    else
        java -Din=$x -Dout=${x%.in}.outhyp m19.app.App;
    fi

    diff -cB -w ${x%.in}.out ${x%.in}.outhyp > ${x%.in}.diff ;
    if [ -s ${x%.in}.diff ]; then
        echo -n "F"
        failures=$failures"Fail: $x: See file ${x%.in}.diff\n" ;
#        echo "FAIL: $x. See file ${x%.in}.diff " ;
    else
        let correct++;
        echo -n "."
        rm -f ${x%.in}.diff ${x%.in}.outhyp ; 
    fi
    let total++;
done

rm -f saved*
let res=100*$correct/$total
echo ""
echo "Total Tests = " $total
echo "Passed = " $res"%"
printf "$failures"
echo "Done."
