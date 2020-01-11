import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recommender-business-requirements',
  templateUrl: './recommender-business-requirements.component.html',
  styleUrls: ['./recommender-business-requirements.component.scss']
})
export class RecommenderBusinessRequirementsComponent implements OnInit {

  test = [1, 2, 3, 4];
  constructor() { }

  ngOnInit() {
  }

  addNew() {
    this.test.push(1);
  }
}
