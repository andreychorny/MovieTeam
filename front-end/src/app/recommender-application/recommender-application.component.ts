import { Component, OnInit } from '@angular/core';
import { RecommenderApplicationService } from './recommender-application.service';

@Component({
  selector: 'app-recommender-application',
  templateUrl: './recommender-application.component.html',
  styleUrls: ['./recommender-application.component.scss']
})
export class RecommenderApplicationComponent implements OnInit {

  constructor(private httpService: RecommenderApplicationService) { }

  ngOnInit() {
  }

  test(): void {
    console.log('wow');
    this.httpService.getMovies('toy story').subscribe(data => {
      console.log(data);
    })
  }

}
