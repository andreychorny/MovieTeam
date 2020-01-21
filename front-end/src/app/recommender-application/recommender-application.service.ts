import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecommenderApplicationService {

  constructor(private httpClient: HttpClient) { }

  getMovies(searchString: string): Observable<any> {
    const fixedString = searchString.replace(' ', '+');
    return this.httpClient.get(`http://localhost:8080/rest/movies?name=${fixedString}`);
  }

  getRecomendation(searchString: string): Observable<any> {
    const fixedString = searchString.replace(' ', '+');
    return this.httpClient.get(`http://localhost:8080/rest/similar?id=${fixedString}`);
  }
}
