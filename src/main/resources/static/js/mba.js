function getgpa($mark, $totalMark) {
    var $point = 0.00;
    var $percentage = ($mark / $totalMark) * 100;
    if ($percentage <= 34) {
        $point = 0.00;
    } else if ($percentage <= 39) {
        $point = 1.00;
    } else if ($percentage <= 44) {
        $point = 1.50;
    } else if ($percentage <= 49) {
        $point = 1.75;
    } else if ($percentage <= 54) {
        $point = 2.00;
    } else if ($percentage <= 59) {
        $point = 2.50;
    } else if ($percentage <= 64) {
        $point = 2.75;
    } else if ($percentage <= 69) {
        $point = 3.0;
    } else if ($percentage <= 74) {
        $point = 3.50;
    } else if ($percentage <= 79) {
        $point = 3.75;
    } else if ($percentage <= 100) {
        $point = 4.0;
    }
    return $point;
}

function getPoint($gpa, $creditHour) {
    return $gpa * $creditHour;
}

function getGPA($gradePoint, $totalCreditHour) {
    return ($gradePoint / $totalCreditHour);
}

function getGrade($gpa) {
    var $grade = '';
    if ($gpa == 0.00) {
        $grade = 'F';
    } else if ($gpa <= 1.00) {
        $grade = 'D';
    } else if ($gpa <= 1.50) {
        $grade = 'D+';
    } else if ($gpa <= 1.75) {
        $grade = 'C-';
    } else if ($gpa <= 2.00) {
        $grade = 'C';
    } else if ($gpa <= 2.50) {
        $grade = 'C+';
    } else if ($gpa <= 2.75) {
        $grade = 'B-';
    } else if ($gpa <= 3.00) {
        $grade = 'B';
    } else if ($gpa <= 3.50) {
        $grade = 'B+';
    } else if ($gpa <= 3.75) {
        $grade = 'A-';
    } else if ($gpa <= 4.00) {
        $grade = 'A';
    }
    return $grade;
}

function getGradePoint($grade) {
    switch ($grade) {
        case 'F':
            return 0.00;
        case 'D':
            return 1.00;
        case 'D+':
            return 1.50;
        case 'C-':
            return 1.75;
        case 'C':
            return 2.00;
        case 'C+':
            return 2.50;
        case 'B-':
            return 2.75;
        case 'B':
            return 3.00;
        case 'B+':
            return 3.50;
        case 'A-':
            return 3.75;
        case 'A':
            return 4.00;
    }
}


function getStanding($gpa) {
    var $status = "";

    if ($gpa < 1.00) {
        $status = 'Fail';
    } else if ($gpa < 2.00) {
        $status = "Marginal Pass";
    } else if ($gpa < 2.00) {
        $status = "Marginal Pass";
    } else if ($gpa < 3.00) {
        $status = "Pass";
    } else if ($gpa < 3.75) {
        $status = "Good";
    } else if ($gpa <= 4.00) {
        $status = "Excellent";
    }
    return $status;
}