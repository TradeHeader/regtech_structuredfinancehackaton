import * as React from "react";
import {useState} from "react";
import {SparklesIcon} from "@heroicons/react/24/solid";
import {Tooltip, TooltipContent, TooltipProvider, TooltipTrigger,} from "@/components/ui/tooltip";
import {cn} from "@/lib/utils";
import {TradeReport} from "@/utils/fetchTradeReports";
import UpdateReportModal from "./UpdateReportModal";
import ShowReportModal from "./ShowReportModal";

import {DocumentCheckIcon, DocumentTextIcon} from "@heroicons/react/16/solid";
import ShowTradeModal from "@/components/ui/ShowTradeModal";

interface TableProps extends React.HTMLAttributes<HTMLTableElement> {
    data: TradeReport[];
}

const Table = React.forwardRef<HTMLTableElement, TableProps>(
    ({className, data, ...props}, ref) => {
        const [selectedReport, setSelectedReport] = useState<TradeReport | null>(
            null
        );
        const [showModal, setShowModal] = useState(false);
        const [showEditor, setShowEditor] = useState(false);
        const [showTradeModal, setShowTradeModal] = useState(false); // State for trade modal

        const handleGenerateUpdatedReport = (report: TradeReport) => {
            setSelectedReport(report);
            setShowModal(true);
        };

        const handleShowReport = (report: TradeReport) => {
            setSelectedReport(report);
            setShowEditor(true);
        };
        const handleShowTrade = (report: TradeReport) => {
            setSelectedReport(report);
            setShowTradeModal(true);
        };

        return (
            <div className="relative w-full overflow-auto">
                <table
                    ref={ref}
                    className={cn("w-full caption-bottom text-sm", className)}
                    {...props}
                >
                    <TableHeader>
                        <TableRow>
                            <TableHead>Report ID</TableHead>
                            <TableHead>Jurisdiction</TableHead>
                            <TableHead>Effective Date</TableHead>
                            <TableHead>Expiration Date</TableHead>
                            <TableHead>Is live</TableHead>
                            <TableHead>Needs Review</TableHead>
                            <TableHead>Actions</TableHead>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        {data.map((report) => (
                            <TableRow key={report.id}>
                                <TableCell>{report.id}</TableCell>
                                <TableCell>{report.jurisdiction}</TableCell>
                                <TableCell className="whitespace-nowrap text-gray-700">
                  <span className="inline-block px-2 py-1 bg-gray-100 rounded">
                    {report.effectiveDate
                        ? new Date(report.effectiveDate).toLocaleDateString()
                        : "N/A"}
                  </span>
                                </TableCell>
                                <TableCell className="whitespace-nowrap text-gray-700">
                  <span className="inline-block px-2 py-1 bg-gray-100 rounded">
                    {report.expirationDate
                        ? new Date(report.expirationDate).toLocaleDateString()
                        : "N/A"}
                  </span>
                                </TableCell>

                                <TableCell>
                                    {report.is_live ? (
                                        <span>YES</span>
                                    ) : (
                                        <span
                                            className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-yellow-100 text-yellow-800">
                      NO
                    </span>
                                    )}
                                </TableCell>
                                <TableCell>
                                    {report.needs_review ? (
                                        <span
                                            className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-red-100 text-red-800">
                      Needs Review
                    </span>
                                    ) : (
                                        <span>No</span>
                                    )}
                                </TableCell>
                                <TableCell className="flex space-x-2">
                                    <TooltipProvider>
                                        <Tooltip>
                                            <TooltipTrigger>
                                                <button
                                                    className="p-2 rounded bg-blue-600 text-white hover:bg-blue-700 transition-colors duration-200 ease-in-out"
                                                    onClick={() => handleShowTrade(report)}
                                                >
                                                    <DocumentTextIcon className="h-5 w-5"/>
                                                </button>
                                            </TooltipTrigger>
                                            <TooltipContent>Show the trade</TooltipContent>
                                        </Tooltip>
                                    </TooltipProvider>
                                    <TooltipProvider>
                                        <Tooltip>
                                            <TooltipTrigger>
                                                <button
                                                    className="p-2 rounded bg-purple-600 text-white hover:bg-purple-700 transition-colors duration-200 ease-in-out"
                                                    onClick={() => handleShowReport(report)}
                                                >
                                                    <DocumentCheckIcon className="h-5 w-5"/>
                                                </button>
                                            </TooltipTrigger>
                                            <TooltipContent>Show the report</TooltipContent>
                                        </Tooltip>
                                    </TooltipProvider>
                                    <TooltipProvider>
                                        <Tooltip>
                                            <TooltipTrigger>
                                                <button
                                                    className="p-2 rounded bg-green-600 text-white hover:bg-green-700 transition-colors duration-200 ease-in-out"
                                                    onClick={() => handleGenerateUpdatedReport(report)}
                                                >
                                                    <SparklesIcon className="h-5 w-5"/>
                                                </button>
                                            </TooltipTrigger>
                                            <TooltipContent>Update the report</TooltipContent>
                                        </Tooltip>
                                    </TooltipProvider>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </table>
                {showModal && selectedReport && (
                    <UpdateReportModal
                        report={selectedReport}
                        onClose={() => setShowModal(false)}
                    />
                )}
                {showEditor && selectedReport && (
                    <ShowReportModal
                        report={selectedReport}
                        onClose={() => setShowEditor(false)}
                    />
                )}
                {showTradeModal && selectedReport && (
                    <ShowTradeModal report={selectedReport} onClose={() => setShowTradeModal(false)}/>
                )}
            </div>
        );
    }
);

Table.displayName = "Table";

const TableHeader = React.forwardRef<
    HTMLTableSectionElement,
    React.HTMLAttributes<HTMLTableSectionElement>
>(({className, ...props}, ref) => (
    <thead ref={ref} className={cn("[&_tr]:border-b", className)} {...props} />
))
TableHeader.displayName = "TableHeader"

const TableBody = React.forwardRef<
    HTMLTableSectionElement,
    React.HTMLAttributes<HTMLTableSectionElement>
>(({className, ...props}, ref) => (
    <tbody
        ref={ref}
        className={cn("[&_tr:last-child]:border-0", className)}
        {...props}
    />
))
TableBody.displayName = "TableBody"

const TableFooter = React.forwardRef<
    HTMLTableSectionElement,
    React.HTMLAttributes<HTMLTableSectionElement>
>(({className, ...props}, ref) => (
    <tfoot
        ref={ref}
        className={cn(
            "border-t bg-gray-100/50 font-medium [&>tr]:last:border-b-0 dark:bg-gray-800/50",
            className
        )}
        {...props}
    />
))
TableFooter.displayName = "TableFooter"

const TableRow = React.forwardRef<
    HTMLTableRowElement,
    React.HTMLAttributes<HTMLTableRowElement>
>(({className, ...props}, ref) => (
    <tr
        ref={ref}
        className={cn(
            "border-b transition-colors hover:bg-gray-100/50 data-[state=selected]:bg-gray-100 dark:hover:bg-gray-800/50 dark:data-[state=selected]:bg-gray-800",
            className
        )}
        {...props}
    />
))
TableRow.displayName = "TableRow"

const TableHead = React.forwardRef<
    HTMLTableCellElement,
    React.ThHTMLAttributes<HTMLTableCellElement>
>(({className, ...props}, ref) => (
    <th
        ref={ref}
        className={cn(
            "h-12 px-4 text-left align-middle font-medium text-gray-500 [&:has([role=checkbox])]:pr-0 dark:text-gray-400",
            className
        )}
        {...props}
    />
))
TableHead.displayName = "TableHead"

const TableCell = React.forwardRef<
    HTMLTableCellElement,
    React.TdHTMLAttributes<HTMLTableCellElement>
>(({className, ...props}, ref) => (
    <td
        ref={ref}
        className={cn("p-4 align-middle [&:has([role=checkbox])]:pr-0", className)}
        {...props}
    />
))
TableCell.displayName = "TableCell"

const TableCaption = React.forwardRef<
    HTMLTableCaptionElement,
    React.HTMLAttributes<HTMLTableCaptionElement>
>(({className, ...props}, ref) => (
    <caption
        ref={ref}
        className={cn("mt-4 text-sm text-gray-500 dark:text-gray-400", className)}
        {...props}
    />
))
TableCaption.displayName = "TableCaption"

export {
    Table,
    TableHeader,
    TableBody,
    TableFooter,
    TableHead,
    TableRow,
    TableCell,
    TableCaption,
};
