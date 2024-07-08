# overlappingevents

This application calculates the overlapping event dates given a list of events.

## Usage

- To run the program use:
```bash
clj -M -m overlappingevents.core input.txt
```

Where `input.txt` is a file created in `resources` folder. In this way, you can modify the `input.txt`
file or create your new files.

## Testing the code

For testing purposes kaocha was added to the project. In the deps.edn file you can find the respective configuration. In summary you can execute the project tests in this way:
```bash
clj -X:test
```