import React from "react";
import {Editor} from "@monaco-editor/react";
import {XMarkIcon} from "@heroicons/react/24/solid";
import {TradeReport} from "@/utils/fetchTradeReports";

interface MonacoEditorModalProps {
    report: TradeReport | null;
    onClose: () => void;
}

const ShowReportModal: React.FC<MonacoEditorModalProps> = ({report, onClose}) => {
    if (!report) return null;

    return (
        <div className="fixed inset-0 bg-gray-800 bg-opacity-75 flex justify-center items-center z-50">
            <div className="bg-white p-4 rounded-lg shadow-lg w-3/4 h-3/4 relative">
                <h2 className="text-xl mb-4">Content of the Trade from report ID: {report.id}</h2>
                <button
                    className="absolute top-4 right-4 text-gray-600 hover:text-gray-800 z-10"
                    onClick={onClose}
                    style={{zIndex: 10}}
                >
                    <XMarkIcon className="h-6 w-6 text-gray-600 hover:text-gray-800"/>
                </button>
                <div>
                    <Editor
                        height="60vh"
                        defaultLanguage="json"
                        value={JSON.stringify(report.trade, null, 2)}
                        options={{
                            readOnly: true,
                        }}
                    />
                </div>
            </div>
        </div>
    );
};

export default ShowReportModal;
