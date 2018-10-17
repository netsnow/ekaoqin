import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class StatisticsBackService {
    url = restUrl.root + restUrl.statisticsBack

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getClaxxByDate(date) {
        return this.http.get(this.url + "/" + date + "/claxx")
    }
    getRoomByDate(date) {
        return this.http.get(this.url + "/" + date + "/room")
    }


}