console.log("app js exe")

// [1] math.js 자료 가져오기
import add from './math.js';
console.log( add(3,4) );

// [2] config.js 자료 가져오기
import config from './config.js';
console.log(config);

// [3] util.js 자료 가져오기
// NamedExport는 중괄호로 묶어서 호출
import hello, {PI, E} from './util.js';
hello('배두훈');
console.log(PI);
console.log(E)


