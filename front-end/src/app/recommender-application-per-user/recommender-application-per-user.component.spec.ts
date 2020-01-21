import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommenderApplicationPerUserComponent } from './recommender-application-per-user.component';

describe('RecommenderApplicationPerUserComponent', () => {
  let component: RecommenderApplicationPerUserComponent;
  let fixture: ComponentFixture<RecommenderApplicationPerUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommenderApplicationPerUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommenderApplicationPerUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
