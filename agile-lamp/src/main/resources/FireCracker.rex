/* FireCracker switch control */
/* Author = Michael Tanzer */
trace n
arg colour state .

/* Give help if required */
if words(colour)=0 | colour='HELP' | colour='?' then do
    say 'Format: FIRECRACKER <A1 | A2> <ON | OFF>'
    exit
    end

/* Validate unit */
select
  when colour='A1' then unitChar = '1'
  when colour='A2' then unitChar = '2'
  when colour='A3' then unitChar = '3'
  when colour='A4' then unitChar = '4'
  otherwise do
    say 'Invalid unit specified.'
    exit 8
    end
  end

/* Validate state */
select
  when state='ON' then stateChar = 'N'
  when state='OFF' then stateChar = 'F'
  otherwise do
    say 'Invalid state specified.'
    exit 9
    end
  end

/* Establish Windows manager and locate FireCracker window */
winMgr = .WindowsManager~new
if winMgr~initcode\=0 then do
    say 'Unable to establish Windows manager.'
    exit 10
    end
winObject = winMgr~Find("FireCracker_BaseFrame")
if winObject=.Nil then do
    say "Unable to find FireCracker window."
    exit 11
    end

/* Locate X10 window */
winObject = winObject~first
do forever
    if winObject==.Nil then do
        say 'Unable to locate X10 window.'
        exit 12
        end
    child = winObject~firstChild
    if child==.Nil then do
        winObject = winObject~next
        iterate
        end
    if child~id\=101 then do
        winObject = winObject~next
        iterate
        end
    if child~wclass\="AfxWnd42s" then do
        winObject = winObject~next
        iterate
        end
    leave                                        /* Desired window located */
    end

/* Send required keystrokes to the window */
child~sendKeyDown('A')
child~sendKeyDown(unitChar)
child~sendKeyDown(stateChar)
exit

::requires "winsystm.cls"
