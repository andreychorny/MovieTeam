import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommenderApplicationComponent } from './recommender-application.component';

describe('RecommenderApplicationComponent', () => {
  let component: RecommenderApplicationComponent;
  let fixture: ComponentFixture<RecommenderApplicationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommenderApplicationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommenderApplicationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
