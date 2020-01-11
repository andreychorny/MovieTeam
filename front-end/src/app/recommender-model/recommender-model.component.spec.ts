import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommenderModelComponent } from './recommender-model.component';

describe('RecommenderModelComponent', () => {
  let component: RecommenderModelComponent;
  let fixture: ComponentFixture<RecommenderModelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommenderModelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommenderModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
