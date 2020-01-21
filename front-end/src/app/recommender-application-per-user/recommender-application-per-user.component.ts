import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { RecommenderApplicationService } from '../recommender-application/recommender-application.service';

@Component({
  selector: 'app-recommender-application-per-user',
  templateUrl: './recommender-application-per-user.component.html',
  styleUrls: ['./recommender-application-per-user.component.scss']
})
export class RecommenderApplicationPerUserComponent implements OnInit {

  inputCtrl: FormControl;
  movies: [];

  constructor(private httpService: RecommenderApplicationService) {
    this.inputCtrl = new FormControl('');
  }

  ngOnInit() {
  }

  getRecomendation(): void {

    this.httpService.getRecomendation(this.inputCtrl.value).subscribe(data => {
      this.movies = data;
    });
  }

}
