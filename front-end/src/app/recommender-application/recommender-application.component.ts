import { Component, OnInit } from '@angular/core';
import { RecommenderApplicationService } from './recommender-application.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-recommender-application',
  templateUrl: './recommender-application.component.html',
  styleUrls: ['./recommender-application.component.scss']
})
export class RecommenderApplicationComponent implements OnInit {
  inputCtrl: FormControl;
  movies: [];

  constructor(private httpService: RecommenderApplicationService) {
    this.inputCtrl = new FormControl('');
  }

  ngOnInit() {
  }

  getMovies(): void {

    this.httpService.getMovies(this.inputCtrl.value).subscribe(data => {
      this.movies = data;
    })
  }

}
