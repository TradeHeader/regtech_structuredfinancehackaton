import React from 'react';
import {DiffEditor} from '@monaco-editor/react';
import {TradeReport} from "@/utils/fetchTradeReports";
import {XMarkIcon} from "@heroicons/react/24/solid";

interface UpdateReportModalProps {
    report: TradeReport;
    onClose: () => void;
}

const UpdateReportModal: React.FC<UpdateReportModalProps> = ({report, onClose}) => {
    const reportJSON = JSON.stringify(report.report, null, 2);

    return (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-75 flex justify-center items-center z-50">
            <div className="bg-white p-4 rounded-lg shadow-lg w-3/4 h-3/4 relative">
                <h2 className="text-xl mb-4">Report Diff for ID: {report.id}</h2>
                <button
                    className="absolute top-4 right-4 text-gray-600 hover:text-gray-800 z-10"
                    onClick={onClose}
                    style={{zIndex: 10}}
                >
                    <XMarkIcon className="h-6 w-6 text-gray-600 hover:text-gray-800"/>
                </button>
                <div>
                    <DiffEditor
                        original={reportJSON}
                        modified={reportJSON}
                        language="json"
                        height="60vh"
                        theme="light"
                    />
                </div>
            </div>
        </div>
    );
};

export default UpdateReportModal;
