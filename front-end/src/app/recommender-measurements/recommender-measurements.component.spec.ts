import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommenderMeasurementsComponent } from './recommender-measurements.component';

describe('RecommenderMeasurementsComponent', () => {
  let component: RecommenderMeasurementsComponent;
  let fixture: ComponentFixture<RecommenderMeasurementsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommenderMeasurementsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommenderMeasurementsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
