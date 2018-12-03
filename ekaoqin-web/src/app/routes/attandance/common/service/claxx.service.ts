import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class ClaxxService {
    claxxUrl = restUrl.root + restUrl.claxx

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllClaxxes(claxxInfo) {
        return this.http.post(this.claxxUrl + "/search", claxxInfo)
    }
    saveClaxx(claxxInfo) {
        return this.http.post(this.claxxUrl, claxxInfo);
    }
    editClaxx(id, claxxInfo) {
        return this.http.put(this.claxxUrl + '/' + id, claxxInfo);
    }
    remove(id: string) {
        return this.http.delete(this.claxxUrl + "/" + id);
    }


}