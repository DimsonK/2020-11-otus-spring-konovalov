jQuery(document).ready(function ($) {

    /** Start Genres Table functions **/
    function genresTableAddRow(evt) {
        evt.preventDefault();

        let rowCount = $('#genresTable > tbody > tr').length;
        if (rowCount && rowCount > 0) {
            let tableBody = $('#genresTable > tbody'),
                lastRowClone = $('tr:last-child', tableBody).clone();
            $('input[type=text]', lastRowClone).val('');
            genresTableRefreshRow(lastRowClone, rowCount);
            tableBody.append(lastRowClone);
        }
        // refresh table indexes
        genresTableRefresh();
        // re enable event for del button
        $('.genreTableDelButton').on('click', function (evt) {
            genresTableDelRow(this, evt);
        });
    }

    function genresTableDelRow(obj, evt) {
        evt.preventDefault();
        const tableId = $(obj).parents('table:first').attr('id');
        const rowCount = $('#' + tableId + ' > tbody > tr').length;
        if (rowCount > 1) {
            let row = $(obj).parent().parent();
            row.remove();
            genresTableRefresh();
        }
    }

    function genresTableRefresh() {
        $('#genresTable > tbody > tr').each(function (index, tr) {
            genresTableRefreshRow(tr, index);
        });
    }

    function genresTableRefreshRow(row, rowId) {
        if (row && rowId) {
            $(row).find('select,input').each(function (index, col) {
                const aName = $(col).attr('name').replace(/([\w.]+\[)(\d+)(]\.\w+)$/i, '$1' + rowId + '$3');
                $(col).attr('name', aName);
                const aId = $(col).attr('id').replace(/([\w.+])(\d+)(\.\w+)$/i, '$1' + rowId + '$3');
                $(col).attr('id', aId);
            })
        }
    }

    /** End Genres Table functions **/

    function addEventListeners() {
        console.log("addEventListeners");

        $('#genreTableAddButton').on('click', function (evt) {
            genresTableAddRow(evt);
        });

        $('.genresTableDelButton').on('click', function (evt) {
            genresTableDelRow(this, evt);
        })
    }

    // run script
    addEventListeners();

});
