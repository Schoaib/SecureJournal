import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Entry } from '@app/_models';

@Injectable({ providedIn: 'root' })
export class EntryService {
    constructor(private http: HttpClient) { }

    // getAll() {
    //   return this.http.get<Entry[]>(`${environment.apiUrl}/entries`);
    // }

    // getById(id: number) {
    //     return this.http.get(`${environment.apiUrl}/entries/${id}`);
    // }

  create (entry: Entry) {
        return this.http.post(`${environment.apiUrl}/entries/create`, entry);
    }

  // update (entry: Entry) {
  //       return this.http.put(`${environment.apiUrl}/entries/${entry.id}`, entry);
  //   }

  //   delete(id: number) {
  //       return this.http.delete(`${environment.apiUrl}/entries/${id}`);
  //   }
}
