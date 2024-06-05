package cdm.regulation;

import cdm.regulation.AddtlAttrbts;
import cdm.regulation.Buyr;
import cdm.regulation.ExctgPrsn;
import cdm.regulation.FinInstrm;
import cdm.regulation.InvstmtDcsnPrsn;
import cdm.regulation.New;
import cdm.regulation.New.NewBuilder;
import cdm.regulation.New.NewBuilderImpl;
import cdm.regulation.New.NewImpl;
import cdm.regulation.OrdrTrnsmssn;
import cdm.regulation.Sellr;
import cdm.regulation.Tx;
import cdm.regulation.meta.NewMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="New", builder=New.NewBuilderImpl.class, version="${project.version}")
public interface New extends RosettaModelObject {

	NewMeta metaData = new NewMeta();

	/*********************** Getter Methods  ***********************/
	String getTxId();
	String getExctgPty();
	String getInvstmtPtyInd();
	String getSubmitgPty();
	Buyr getBuyr();
	Sellr getSellr();
	OrdrTrnsmssn getOrdrTrnsmssn();
	Tx getTx();
	FinInstrm getFinInstrm();
	InvstmtDcsnPrsn getInvstmtDcsnPrsn();
	ExctgPrsn getExctgPrsn();
	AddtlAttrbts getAddtlAttrbts();

	/*********************** Build Methods  ***********************/
	New build();
	
	New.NewBuilder toBuilder();
	
	static New.NewBuilder builder() {
		return new New.NewBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends New> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends New> getType() {
		return New.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("txId"), String.class, getTxId(), this);
		processor.processBasic(path.newSubPath("exctgPty"), String.class, getExctgPty(), this);
		processor.processBasic(path.newSubPath("invstmtPtyInd"), String.class, getInvstmtPtyInd(), this);
		processor.processBasic(path.newSubPath("submitgPty"), String.class, getSubmitgPty(), this);
		processRosetta(path.newSubPath("buyr"), processor, Buyr.class, getBuyr());
		processRosetta(path.newSubPath("sellr"), processor, Sellr.class, getSellr());
		processRosetta(path.newSubPath("ordrTrnsmssn"), processor, OrdrTrnsmssn.class, getOrdrTrnsmssn());
		processRosetta(path.newSubPath("tx"), processor, Tx.class, getTx());
		processRosetta(path.newSubPath("finInstrm"), processor, FinInstrm.class, getFinInstrm());
		processRosetta(path.newSubPath("invstmtDcsnPrsn"), processor, InvstmtDcsnPrsn.class, getInvstmtDcsnPrsn());
		processRosetta(path.newSubPath("exctgPrsn"), processor, ExctgPrsn.class, getExctgPrsn());
		processRosetta(path.newSubPath("addtlAttrbts"), processor, AddtlAttrbts.class, getAddtlAttrbts());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NewBuilder extends New, RosettaModelObjectBuilder {
		Buyr.BuyrBuilder getOrCreateBuyr();
		Buyr.BuyrBuilder getBuyr();
		Sellr.SellrBuilder getOrCreateSellr();
		Sellr.SellrBuilder getSellr();
		OrdrTrnsmssn.OrdrTrnsmssnBuilder getOrCreateOrdrTrnsmssn();
		OrdrTrnsmssn.OrdrTrnsmssnBuilder getOrdrTrnsmssn();
		Tx.TxBuilder getOrCreateTx();
		Tx.TxBuilder getTx();
		FinInstrm.FinInstrmBuilder getOrCreateFinInstrm();
		FinInstrm.FinInstrmBuilder getFinInstrm();
		InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder getOrCreateInvstmtDcsnPrsn();
		InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder getInvstmtDcsnPrsn();
		ExctgPrsn.ExctgPrsnBuilder getOrCreateExctgPrsn();
		ExctgPrsn.ExctgPrsnBuilder getExctgPrsn();
		AddtlAttrbts.AddtlAttrbtsBuilder getOrCreateAddtlAttrbts();
		AddtlAttrbts.AddtlAttrbtsBuilder getAddtlAttrbts();
		New.NewBuilder setTxId(String txId);
		New.NewBuilder setExctgPty(String exctgPty);
		New.NewBuilder setInvstmtPtyInd(String invstmtPtyInd);
		New.NewBuilder setSubmitgPty(String submitgPty);
		New.NewBuilder setBuyr(Buyr buyr);
		New.NewBuilder setSellr(Sellr sellr);
		New.NewBuilder setOrdrTrnsmssn(OrdrTrnsmssn ordrTrnsmssn);
		New.NewBuilder setTx(Tx tx);
		New.NewBuilder setFinInstrm(FinInstrm finInstrm);
		New.NewBuilder setInvstmtDcsnPrsn(InvstmtDcsnPrsn invstmtDcsnPrsn);
		New.NewBuilder setExctgPrsn(ExctgPrsn exctgPrsn);
		New.NewBuilder setAddtlAttrbts(AddtlAttrbts addtlAttrbts);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("txId"), String.class, getTxId(), this);
			processor.processBasic(path.newSubPath("exctgPty"), String.class, getExctgPty(), this);
			processor.processBasic(path.newSubPath("invstmtPtyInd"), String.class, getInvstmtPtyInd(), this);
			processor.processBasic(path.newSubPath("submitgPty"), String.class, getSubmitgPty(), this);
			processRosetta(path.newSubPath("buyr"), processor, Buyr.BuyrBuilder.class, getBuyr());
			processRosetta(path.newSubPath("sellr"), processor, Sellr.SellrBuilder.class, getSellr());
			processRosetta(path.newSubPath("ordrTrnsmssn"), processor, OrdrTrnsmssn.OrdrTrnsmssnBuilder.class, getOrdrTrnsmssn());
			processRosetta(path.newSubPath("tx"), processor, Tx.TxBuilder.class, getTx());
			processRosetta(path.newSubPath("finInstrm"), processor, FinInstrm.FinInstrmBuilder.class, getFinInstrm());
			processRosetta(path.newSubPath("invstmtDcsnPrsn"), processor, InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder.class, getInvstmtDcsnPrsn());
			processRosetta(path.newSubPath("exctgPrsn"), processor, ExctgPrsn.ExctgPrsnBuilder.class, getExctgPrsn());
			processRosetta(path.newSubPath("addtlAttrbts"), processor, AddtlAttrbts.AddtlAttrbtsBuilder.class, getAddtlAttrbts());
		}
		

		New.NewBuilder prune();
	}

	/*********************** Immutable Implementation of New  ***********************/
	class NewImpl implements New {
		private final String txId;
		private final String exctgPty;
		private final String invstmtPtyInd;
		private final String submitgPty;
		private final Buyr buyr;
		private final Sellr sellr;
		private final OrdrTrnsmssn ordrTrnsmssn;
		private final Tx tx;
		private final FinInstrm finInstrm;
		private final InvstmtDcsnPrsn invstmtDcsnPrsn;
		private final ExctgPrsn exctgPrsn;
		private final AddtlAttrbts addtlAttrbts;
		
		protected NewImpl(New.NewBuilder builder) {
			this.txId = builder.getTxId();
			this.exctgPty = builder.getExctgPty();
			this.invstmtPtyInd = builder.getInvstmtPtyInd();
			this.submitgPty = builder.getSubmitgPty();
			this.buyr = ofNullable(builder.getBuyr()).map(f->f.build()).orElse(null);
			this.sellr = ofNullable(builder.getSellr()).map(f->f.build()).orElse(null);
			this.ordrTrnsmssn = ofNullable(builder.getOrdrTrnsmssn()).map(f->f.build()).orElse(null);
			this.tx = ofNullable(builder.getTx()).map(f->f.build()).orElse(null);
			this.finInstrm = ofNullable(builder.getFinInstrm()).map(f->f.build()).orElse(null);
			this.invstmtDcsnPrsn = ofNullable(builder.getInvstmtDcsnPrsn()).map(f->f.build()).orElse(null);
			this.exctgPrsn = ofNullable(builder.getExctgPrsn()).map(f->f.build()).orElse(null);
			this.addtlAttrbts = ofNullable(builder.getAddtlAttrbts()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("txId")
		public String getTxId() {
			return txId;
		}
		
		@Override
		@RosettaAttribute("exctgPty")
		public String getExctgPty() {
			return exctgPty;
		}
		
		@Override
		@RosettaAttribute("invstmtPtyInd")
		public String getInvstmtPtyInd() {
			return invstmtPtyInd;
		}
		
		@Override
		@RosettaAttribute("submitgPty")
		public String getSubmitgPty() {
			return submitgPty;
		}
		
		@Override
		@RosettaAttribute("buyr")
		public Buyr getBuyr() {
			return buyr;
		}
		
		@Override
		@RosettaAttribute("sellr")
		public Sellr getSellr() {
			return sellr;
		}
		
		@Override
		@RosettaAttribute("ordrTrnsmssn")
		public OrdrTrnsmssn getOrdrTrnsmssn() {
			return ordrTrnsmssn;
		}
		
		@Override
		@RosettaAttribute("tx")
		public Tx getTx() {
			return tx;
		}
		
		@Override
		@RosettaAttribute("finInstrm")
		public FinInstrm getFinInstrm() {
			return finInstrm;
		}
		
		@Override
		@RosettaAttribute("invstmtDcsnPrsn")
		public InvstmtDcsnPrsn getInvstmtDcsnPrsn() {
			return invstmtDcsnPrsn;
		}
		
		@Override
		@RosettaAttribute("exctgPrsn")
		public ExctgPrsn getExctgPrsn() {
			return exctgPrsn;
		}
		
		@Override
		@RosettaAttribute("addtlAttrbts")
		public AddtlAttrbts getAddtlAttrbts() {
			return addtlAttrbts;
		}
		
		@Override
		public New build() {
			return this;
		}
		
		@Override
		public New.NewBuilder toBuilder() {
			New.NewBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(New.NewBuilder builder) {
			ofNullable(getTxId()).ifPresent(builder::setTxId);
			ofNullable(getExctgPty()).ifPresent(builder::setExctgPty);
			ofNullable(getInvstmtPtyInd()).ifPresent(builder::setInvstmtPtyInd);
			ofNullable(getSubmitgPty()).ifPresent(builder::setSubmitgPty);
			ofNullable(getBuyr()).ifPresent(builder::setBuyr);
			ofNullable(getSellr()).ifPresent(builder::setSellr);
			ofNullable(getOrdrTrnsmssn()).ifPresent(builder::setOrdrTrnsmssn);
			ofNullable(getTx()).ifPresent(builder::setTx);
			ofNullable(getFinInstrm()).ifPresent(builder::setFinInstrm);
			ofNullable(getInvstmtDcsnPrsn()).ifPresent(builder::setInvstmtDcsnPrsn);
			ofNullable(getExctgPrsn()).ifPresent(builder::setExctgPrsn);
			ofNullable(getAddtlAttrbts()).ifPresent(builder::setAddtlAttrbts);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			New _that = getType().cast(o);
		
			if (!Objects.equals(txId, _that.getTxId())) return false;
			if (!Objects.equals(exctgPty, _that.getExctgPty())) return false;
			if (!Objects.equals(invstmtPtyInd, _that.getInvstmtPtyInd())) return false;
			if (!Objects.equals(submitgPty, _that.getSubmitgPty())) return false;
			if (!Objects.equals(buyr, _that.getBuyr())) return false;
			if (!Objects.equals(sellr, _that.getSellr())) return false;
			if (!Objects.equals(ordrTrnsmssn, _that.getOrdrTrnsmssn())) return false;
			if (!Objects.equals(tx, _that.getTx())) return false;
			if (!Objects.equals(finInstrm, _that.getFinInstrm())) return false;
			if (!Objects.equals(invstmtDcsnPrsn, _that.getInvstmtDcsnPrsn())) return false;
			if (!Objects.equals(exctgPrsn, _that.getExctgPrsn())) return false;
			if (!Objects.equals(addtlAttrbts, _that.getAddtlAttrbts())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (txId != null ? txId.hashCode() : 0);
			_result = 31 * _result + (exctgPty != null ? exctgPty.hashCode() : 0);
			_result = 31 * _result + (invstmtPtyInd != null ? invstmtPtyInd.hashCode() : 0);
			_result = 31 * _result + (submitgPty != null ? submitgPty.hashCode() : 0);
			_result = 31 * _result + (buyr != null ? buyr.hashCode() : 0);
			_result = 31 * _result + (sellr != null ? sellr.hashCode() : 0);
			_result = 31 * _result + (ordrTrnsmssn != null ? ordrTrnsmssn.hashCode() : 0);
			_result = 31 * _result + (tx != null ? tx.hashCode() : 0);
			_result = 31 * _result + (finInstrm != null ? finInstrm.hashCode() : 0);
			_result = 31 * _result + (invstmtDcsnPrsn != null ? invstmtDcsnPrsn.hashCode() : 0);
			_result = 31 * _result + (exctgPrsn != null ? exctgPrsn.hashCode() : 0);
			_result = 31 * _result + (addtlAttrbts != null ? addtlAttrbts.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "New {" +
				"txId=" + this.txId + ", " +
				"exctgPty=" + this.exctgPty + ", " +
				"invstmtPtyInd=" + this.invstmtPtyInd + ", " +
				"submitgPty=" + this.submitgPty + ", " +
				"buyr=" + this.buyr + ", " +
				"sellr=" + this.sellr + ", " +
				"ordrTrnsmssn=" + this.ordrTrnsmssn + ", " +
				"tx=" + this.tx + ", " +
				"finInstrm=" + this.finInstrm + ", " +
				"invstmtDcsnPrsn=" + this.invstmtDcsnPrsn + ", " +
				"exctgPrsn=" + this.exctgPrsn + ", " +
				"addtlAttrbts=" + this.addtlAttrbts +
			'}';
		}
	}

	/*********************** Builder Implementation of New  ***********************/
	class NewBuilderImpl implements New.NewBuilder {
	
		protected String txId;
		protected String exctgPty;
		protected String invstmtPtyInd;
		protected String submitgPty;
		protected Buyr.BuyrBuilder buyr;
		protected Sellr.SellrBuilder sellr;
		protected OrdrTrnsmssn.OrdrTrnsmssnBuilder ordrTrnsmssn;
		protected Tx.TxBuilder tx;
		protected FinInstrm.FinInstrmBuilder finInstrm;
		protected InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder invstmtDcsnPrsn;
		protected ExctgPrsn.ExctgPrsnBuilder exctgPrsn;
		protected AddtlAttrbts.AddtlAttrbtsBuilder addtlAttrbts;
	
		public NewBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("txId")
		public String getTxId() {
			return txId;
		}
		
		@Override
		@RosettaAttribute("exctgPty")
		public String getExctgPty() {
			return exctgPty;
		}
		
		@Override
		@RosettaAttribute("invstmtPtyInd")
		public String getInvstmtPtyInd() {
			return invstmtPtyInd;
		}
		
		@Override
		@RosettaAttribute("submitgPty")
		public String getSubmitgPty() {
			return submitgPty;
		}
		
		@Override
		@RosettaAttribute("buyr")
		public Buyr.BuyrBuilder getBuyr() {
			return buyr;
		}
		
		@Override
		public Buyr.BuyrBuilder getOrCreateBuyr() {
			Buyr.BuyrBuilder result;
			if (buyr!=null) {
				result = buyr;
			}
			else {
				result = buyr = Buyr.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("sellr")
		public Sellr.SellrBuilder getSellr() {
			return sellr;
		}
		
		@Override
		public Sellr.SellrBuilder getOrCreateSellr() {
			Sellr.SellrBuilder result;
			if (sellr!=null) {
				result = sellr;
			}
			else {
				result = sellr = Sellr.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("ordrTrnsmssn")
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder getOrdrTrnsmssn() {
			return ordrTrnsmssn;
		}
		
		@Override
		public OrdrTrnsmssn.OrdrTrnsmssnBuilder getOrCreateOrdrTrnsmssn() {
			OrdrTrnsmssn.OrdrTrnsmssnBuilder result;
			if (ordrTrnsmssn!=null) {
				result = ordrTrnsmssn;
			}
			else {
				result = ordrTrnsmssn = OrdrTrnsmssn.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("tx")
		public Tx.TxBuilder getTx() {
			return tx;
		}
		
		@Override
		public Tx.TxBuilder getOrCreateTx() {
			Tx.TxBuilder result;
			if (tx!=null) {
				result = tx;
			}
			else {
				result = tx = Tx.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finInstrm")
		public FinInstrm.FinInstrmBuilder getFinInstrm() {
			return finInstrm;
		}
		
		@Override
		public FinInstrm.FinInstrmBuilder getOrCreateFinInstrm() {
			FinInstrm.FinInstrmBuilder result;
			if (finInstrm!=null) {
				result = finInstrm;
			}
			else {
				result = finInstrm = FinInstrm.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("invstmtDcsnPrsn")
		public InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder getInvstmtDcsnPrsn() {
			return invstmtDcsnPrsn;
		}
		
		@Override
		public InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder getOrCreateInvstmtDcsnPrsn() {
			InvstmtDcsnPrsn.InvstmtDcsnPrsnBuilder result;
			if (invstmtDcsnPrsn!=null) {
				result = invstmtDcsnPrsn;
			}
			else {
				result = invstmtDcsnPrsn = InvstmtDcsnPrsn.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exctgPrsn")
		public ExctgPrsn.ExctgPrsnBuilder getExctgPrsn() {
			return exctgPrsn;
		}
		
		@Override
		public ExctgPrsn.ExctgPrsnBuilder getOrCreateExctgPrsn() {
			ExctgPrsn.ExctgPrsnBuilder result;
			if (exctgPrsn!=null) {
				result = exctgPrsn;
			}
			else {
				result = exctgPrsn = ExctgPrsn.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("addtlAttrbts")
		public AddtlAttrbts.AddtlAttrbtsBuilder getAddtlAttrbts() {
			return addtlAttrbts;
		}
		
		@Override
		public AddtlAttrbts.AddtlAttrbtsBuilder getOrCreateAddtlAttrbts() {
			AddtlAttrbts.AddtlAttrbtsBuilder result;
			if (addtlAttrbts!=null) {
				result = addtlAttrbts;
			}
			else {
				result = addtlAttrbts = AddtlAttrbts.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("txId")
		public New.NewBuilder setTxId(String txId) {
			this.txId = txId==null?null:txId;
			return this;
		}
		@Override
		@RosettaAttribute("exctgPty")
		public New.NewBuilder setExctgPty(String exctgPty) {
			this.exctgPty = exctgPty==null?null:exctgPty;
			return this;
		}
		@Override
		@RosettaAttribute("invstmtPtyInd")
		public New.NewBuilder setInvstmtPtyInd(String invstmtPtyInd) {
			this.invstmtPtyInd = invstmtPtyInd==null?null:invstmtPtyInd;
			return this;
		}
		@Override
		@RosettaAttribute("submitgPty")
		public New.NewBuilder setSubmitgPty(String submitgPty) {
			this.submitgPty = submitgPty==null?null:submitgPty;
			return this;
		}
		@Override
		@RosettaAttribute("buyr")
		public New.NewBuilder setBuyr(Buyr buyr) {
			this.buyr = buyr==null?null:buyr.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("sellr")
		public New.NewBuilder setSellr(Sellr sellr) {
			this.sellr = sellr==null?null:sellr.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("ordrTrnsmssn")
		public New.NewBuilder setOrdrTrnsmssn(OrdrTrnsmssn ordrTrnsmssn) {
			this.ordrTrnsmssn = ordrTrnsmssn==null?null:ordrTrnsmssn.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("tx")
		public New.NewBuilder setTx(Tx tx) {
			this.tx = tx==null?null:tx.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("finInstrm")
		public New.NewBuilder setFinInstrm(FinInstrm finInstrm) {
			this.finInstrm = finInstrm==null?null:finInstrm.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("invstmtDcsnPrsn")
		public New.NewBuilder setInvstmtDcsnPrsn(InvstmtDcsnPrsn invstmtDcsnPrsn) {
			this.invstmtDcsnPrsn = invstmtDcsnPrsn==null?null:invstmtDcsnPrsn.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exctgPrsn")
		public New.NewBuilder setExctgPrsn(ExctgPrsn exctgPrsn) {
			this.exctgPrsn = exctgPrsn==null?null:exctgPrsn.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("addtlAttrbts")
		public New.NewBuilder setAddtlAttrbts(AddtlAttrbts addtlAttrbts) {
			this.addtlAttrbts = addtlAttrbts==null?null:addtlAttrbts.toBuilder();
			return this;
		}
		
		@Override
		public New build() {
			return new New.NewImpl(this);
		}
		
		@Override
		public New.NewBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public New.NewBuilder prune() {
			if (buyr!=null && !buyr.prune().hasData()) buyr = null;
			if (sellr!=null && !sellr.prune().hasData()) sellr = null;
			if (ordrTrnsmssn!=null && !ordrTrnsmssn.prune().hasData()) ordrTrnsmssn = null;
			if (tx!=null && !tx.prune().hasData()) tx = null;
			if (finInstrm!=null && !finInstrm.prune().hasData()) finInstrm = null;
			if (invstmtDcsnPrsn!=null && !invstmtDcsnPrsn.prune().hasData()) invstmtDcsnPrsn = null;
			if (exctgPrsn!=null && !exctgPrsn.prune().hasData()) exctgPrsn = null;
			if (addtlAttrbts!=null && !addtlAttrbts.prune().hasData()) addtlAttrbts = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getTxId()!=null) return true;
			if (getExctgPty()!=null) return true;
			if (getInvstmtPtyInd()!=null) return true;
			if (getSubmitgPty()!=null) return true;
			if (getBuyr()!=null && getBuyr().hasData()) return true;
			if (getSellr()!=null && getSellr().hasData()) return true;
			if (getOrdrTrnsmssn()!=null && getOrdrTrnsmssn().hasData()) return true;
			if (getTx()!=null && getTx().hasData()) return true;
			if (getFinInstrm()!=null && getFinInstrm().hasData()) return true;
			if (getInvstmtDcsnPrsn()!=null && getInvstmtDcsnPrsn().hasData()) return true;
			if (getExctgPrsn()!=null && getExctgPrsn().hasData()) return true;
			if (getAddtlAttrbts()!=null && getAddtlAttrbts().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public New.NewBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			New.NewBuilder o = (New.NewBuilder) other;
			
			merger.mergeRosetta(getBuyr(), o.getBuyr(), this::setBuyr);
			merger.mergeRosetta(getSellr(), o.getSellr(), this::setSellr);
			merger.mergeRosetta(getOrdrTrnsmssn(), o.getOrdrTrnsmssn(), this::setOrdrTrnsmssn);
			merger.mergeRosetta(getTx(), o.getTx(), this::setTx);
			merger.mergeRosetta(getFinInstrm(), o.getFinInstrm(), this::setFinInstrm);
			merger.mergeRosetta(getInvstmtDcsnPrsn(), o.getInvstmtDcsnPrsn(), this::setInvstmtDcsnPrsn);
			merger.mergeRosetta(getExctgPrsn(), o.getExctgPrsn(), this::setExctgPrsn);
			merger.mergeRosetta(getAddtlAttrbts(), o.getAddtlAttrbts(), this::setAddtlAttrbts);
			
			merger.mergeBasic(getTxId(), o.getTxId(), this::setTxId);
			merger.mergeBasic(getExctgPty(), o.getExctgPty(), this::setExctgPty);
			merger.mergeBasic(getInvstmtPtyInd(), o.getInvstmtPtyInd(), this::setInvstmtPtyInd);
			merger.mergeBasic(getSubmitgPty(), o.getSubmitgPty(), this::setSubmitgPty);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			New _that = getType().cast(o);
		
			if (!Objects.equals(txId, _that.getTxId())) return false;
			if (!Objects.equals(exctgPty, _that.getExctgPty())) return false;
			if (!Objects.equals(invstmtPtyInd, _that.getInvstmtPtyInd())) return false;
			if (!Objects.equals(submitgPty, _that.getSubmitgPty())) return false;
			if (!Objects.equals(buyr, _that.getBuyr())) return false;
			if (!Objects.equals(sellr, _that.getSellr())) return false;
			if (!Objects.equals(ordrTrnsmssn, _that.getOrdrTrnsmssn())) return false;
			if (!Objects.equals(tx, _that.getTx())) return false;
			if (!Objects.equals(finInstrm, _that.getFinInstrm())) return false;
			if (!Objects.equals(invstmtDcsnPrsn, _that.getInvstmtDcsnPrsn())) return false;
			if (!Objects.equals(exctgPrsn, _that.getExctgPrsn())) return false;
			if (!Objects.equals(addtlAttrbts, _that.getAddtlAttrbts())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (txId != null ? txId.hashCode() : 0);
			_result = 31 * _result + (exctgPty != null ? exctgPty.hashCode() : 0);
			_result = 31 * _result + (invstmtPtyInd != null ? invstmtPtyInd.hashCode() : 0);
			_result = 31 * _result + (submitgPty != null ? submitgPty.hashCode() : 0);
			_result = 31 * _result + (buyr != null ? buyr.hashCode() : 0);
			_result = 31 * _result + (sellr != null ? sellr.hashCode() : 0);
			_result = 31 * _result + (ordrTrnsmssn != null ? ordrTrnsmssn.hashCode() : 0);
			_result = 31 * _result + (tx != null ? tx.hashCode() : 0);
			_result = 31 * _result + (finInstrm != null ? finInstrm.hashCode() : 0);
			_result = 31 * _result + (invstmtDcsnPrsn != null ? invstmtDcsnPrsn.hashCode() : 0);
			_result = 31 * _result + (exctgPrsn != null ? exctgPrsn.hashCode() : 0);
			_result = 31 * _result + (addtlAttrbts != null ? addtlAttrbts.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NewBuilder {" +
				"txId=" + this.txId + ", " +
				"exctgPty=" + this.exctgPty + ", " +
				"invstmtPtyInd=" + this.invstmtPtyInd + ", " +
				"submitgPty=" + this.submitgPty + ", " +
				"buyr=" + this.buyr + ", " +
				"sellr=" + this.sellr + ", " +
				"ordrTrnsmssn=" + this.ordrTrnsmssn + ", " +
				"tx=" + this.tx + ", " +
				"finInstrm=" + this.finInstrm + ", " +
				"invstmtDcsnPrsn=" + this.invstmtDcsnPrsn + ", " +
				"exctgPrsn=" + this.exctgPrsn + ", " +
				"addtlAttrbts=" + this.addtlAttrbts +
			'}';
		}
	}
}
