import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { HttpClient } from '@angular/common/http';
import { restUrl } from '../constant/constant';

@Injectable({
    providedIn: 'root',
})
export class EntryService {
    url = restUrl.root + restUrl.entry

    constructor(
        private msg: NzMessageService,
        private http: HttpClient,
    ) { }
    getAllEntryLogs() {
        return this.http.get(this.url)
    }



}