import { TestBed } from '@angular/core/testing';

import { RecommenderApplicationService } from './recommender-application.service';

describe('RecommenderApplicationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RecommenderApplicationService = TestBed.get(RecommenderApplicationService);
    expect(service).toBeTruthy();
  });
});
