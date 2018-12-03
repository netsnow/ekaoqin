import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class StudentService {
    studentUrl = restUrl.root + restUrl.student

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllStudents(key) {
        return this.http.get(this.studentUrl+"/fuzzySearch?key="+key)
    }
    saveStudent(studentInfo) {
        return this.http.post(this.studentUrl, studentInfo);
    }
    editStudent(id, studentInfo) {
        return this.http.put(this.studentUrl + '/' + id, studentInfo);
    }
    remove(id: string) {
        return this.http.delete(this.studentUrl + "/" + id);
    }


}