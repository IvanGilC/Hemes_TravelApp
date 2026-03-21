# Sprint 2 Retrospective - Hermes Travel App

**Project:** Hermes Travel App  
**Team:** Ivan Gil Cañizares, Marco Beruet Morelli

## 1. Accomplishments (Reviewing the Backlog)

Following the **plan_sprint02.md**, we have successfully completed all tasks of the logic and testing phase:

- **Trip Management Logic (T1.1 - Ivan & Marco):**
  - Implemented full in-memory CRUD operations for trips (`addTrip`, `editTrip`, `deleteTrip`) following the MVVM pattern.
  - Each trip contains the required fields: `title`, `startDate`, `endDate`, and `description`.
  - The architecture flow `UI → ViewModel → TripRepository → TripRepositoryImpl → FakeTripDataSource` is fully respected.

- **Activity/Itinerary Logic (T1.2 - Ivan & Marco):**
  - Implemented full in-memory CRUD operations for itinerary activities (`addActivity`, `updateActivity`, `deleteActivity`).
  - Each activity contains: `title`, `description`, `date (LocalDate)`, and `time (LocalTime)`.

- **Data Validation (T1.3 - Ivan & Marco):**
  - All date fields use `DatePicker` components — free text input is not allowed.
  - Enforced that trip `startDate` must be before `endDate`.
  - Enforced that activity dates must fall within the parent trip's date range.
  - All required fields are validated before submission.

- **User Settings with SharedPreferences (T1.4 - Ivan & Marco):**
  - Implemented persistent user settings using Android `SharedPreferences`.
  - Settings are automatically loaded on app start.
  - Stored settings include: `username`, `date of birth`, `dark mode (boolean)`, and `application language`.

- **Multi-language Support (T1.5 - Ivan & Marco):**
  - Implemented support for a minimum of 3 languages: English (`en`), Catalan (`ca`), and Spanish (`es`).

- **Itinerary Flow (T2 - Ivan & Marco):**
  - Structured the full navigation flow: `Menu → Travel List → Trip Detail → Itinerary (CRUD)`.
  - UI updates reflect dynamically in both the trip list and the itinerary list after every operation.

- **Data Validation & Testing (T3 - Ivan & Marco):**
  - Input validation implemented at both the ViewModel and UI layers.
  - Clear error messages displayed for: empty required fields, invalid dates, and activities outside the trip range.
  - Written and passed **38 unit tests** across 8 test classes — all passing on first execution with no code changes required.
  - Added `Log` statements across all CRUD operations at the correct levels (`DEBUG`, `INFO`, `ERROR`), verified via Logcat.
  - Used `mockStatic(Log::class)` in all test classes to ensure JVM compatibility without Android stubs.

## 2. Technical Review

- **Architecture:** The MVVM pattern is fully implemented and respected across all layers. No business logic was placed directly in the UI layer.
- **In-Memory Storage:** Data is managed using a `FakeTripDataSource` and `FakeActivityDataSource` backed by mutable lists, following the singleton pattern as specified.
- **Testing Stack:** JUnit4, `InstantTaskExecutorRule`, `mockStatic` (MockK), and `kotlinx-coroutines-test` were used to achieve full test coverage.
- **Test Results:** 38 tests across 8 classes — 0 failures, 0 errors.

| Test Class | Passed | Total |
|---|---|---|
| `FakeTripDataSourceTest` | 5 | 5 |
| `FakeActivityDataSourceTest` | 6 | 6 |
| `TripRepositoryImplTest` | 4 | 4 |
| `TripViewModelTest` | 6 | 6 |
| `TripListViewModelTest` | 5 | 5 |
| `ActivityViewModelTest` | 4 | 4 |
| `TripCrudLoggingTest` | 3 | 3 |
| `ValidationTest` | 5 | 5 |
| **TOTAL** | **38** | **38** |

## 3. Retrospective Analysis

### What went well?

- **Task Distribution:** The 50/50 workload split maintained from Sprint 01 worked well again. Both team members contributed equally across logic, validation, and testing.
- **Test Coverage:** All 38 unit tests passed on first execution without requiring any fixes, reflecting the quality of the implementation.
- **Architecture Compliance:** The `UI → ViewModel → Repository → DataSource` flow was consistently respected throughout, making the codebase clean and testable.
- **Validation Robustness:** Date constraints and required field checks are enforced at both the ViewModel and UI layers, providing clear user feedback in all error cases.
- **No Blockers:** The sprint was completed without significant technical blockers or unexpected issues.

### What can be improved?

- **In-Memory Persistence:** As planned for Sprint 02, data is still stored only in memory and is lost on app restart. In Sprint 03, we need to transition to a persistent storage solution (e.g., Room database).
- **UI Polish:** The current UI is functional but not fully styled. Sprint 03 will be the opportunity to apply a consistent visual design across all screens.
- **Test Scope:** Current tests cover unit logic only. Future sprints should include instrumented UI tests to simulate full user interaction flows.

## 4. Final Thoughts

All goals set in `plan_sprint02.md` have been met. The application now has a fully functional in-memory CRUD system for trips and activities, proper data validation, persistent user settings, multi-language support, and a complete suite of 38 passing unit tests. We are ready to begin Sprint 03: persistent storage and UI refinement.

---
**Prepared by:** Ivan Gil Cañizares & Marco Beruet Morelli
