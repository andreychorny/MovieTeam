import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommenderConclusionComponent } from './recommender-conclusion.component';

describe('RecommenderConclusionComponent', () => {
  let component: RecommenderConclusionComponent;
  let fixture: ComponentFixture<RecommenderConclusionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommenderConclusionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommenderConclusionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
